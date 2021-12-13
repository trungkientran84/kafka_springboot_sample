package com.t.t.k.ims.service.orders;


import com.t.t.k.ims.common.enums.OrderStatus;
import com.t.t.k.ims.common.utils.ModelMapper;
import com.t.t.k.ims.common.utils.ObjectMapper;
import com.t.t.k.ims.controller.orders.dto.ItemDTO;
import com.t.t.k.ims.controller.orders.dto.OrderDTO;
import com.t.t.k.ims.controller.orders.dto.PaymentDTO;
import com.t.t.k.ims.kafka.KafkaMessageProduceEvent;
import com.t.t.k.ims.model.orders.Item;
import com.t.t.k.ims.model.orders.Order;
import com.t.t.k.ims.model.orders.Payment;
import com.t.t.k.ims.model.products.Product;
import com.t.t.k.ims.repository.orders.OrderRepository;
import com.t.t.k.ims.service.products.ProductService;
import com.t.t.k.ims.validation.exception.ApiException;
import com.t.t.k.ims.validation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * This class implement all business methods available on orders
 *
 * @author ttkien
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final ApplicationEventMulticaster eventPublisher;

    @Value("${app.config.kafka.topics.order}")
    private String ORDER_TOPIC;

    @Value("${app.config.tax-percent}")
    private BigDecimal TAX_PERCENT;

    /**
     * The constructor to create instance of this class
     *
     * @param orderRepository the dependent repository
     * @param productService  the dependent service
     * @param eventPublisher  the dependent event publisher
     */
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, ApplicationEventMulticaster eventPublisher) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Add a new order
     *
     * @param dto the object contains request data
     * @return created merchant order
     */
    @Override
    public Order addOrder(OrderDTO dto) {

        Order c = new Order();

        ModelMapper.intance().map(dto, c);

        return orderRepository.save(c);

    }

    /**
     * Delete a order by id
     *
     * @param id the order id to delete
     * @throws Exception when error
     */
    @Override
    @Transactional
    public void deleteOrder(String id) throws Exception {
        Order o = orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Order", id));

        if (OrderStatus.COMPLETED.equals(o.getStatus()) || OrderStatus.CANCELED.equals(o.getStatus())) {
            throw new ApiException("It only allows to delete an order if its status is not COMPLETED and CANCELED. Order Status: " + o.getStatus());
        }

        /**
         * Publish kafka event to produce the order message to kafka server.
         * Kafka consumer will process the order message to:
         * 1. revert inventory if there is any purchased item
         * 2. refund to customer if there is any payment
         */
        o.setStatus(OrderStatus.DELETED);
        eventPublisher.multicastEvent(new KafkaMessageProduceEvent(ORDER_TOPIC, o.getId(), ObjectMapper.instance().writeValueAsString(o)));


        orderRepository.deleteById(id);
    }

    /**
     * Find a order by id.
     *
     * @param id the id of target order
     * @return the order data model
     * @throws Exception if there is any error
     */
    @Override
    public Order findById(String id) throws Exception {
        return orderRepository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Order", id));
    }

    /**
     * Get all orders.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of orders
     */
    @Override
    public Page<Order> findAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    /**
     * find all orders by provided product upc number.
     *
     * @param upc      the product upc number
     * @param pageable The object contains pageable information
     * @return the pageable list of orders
     */
    @Override
    public Page<Order> findAllOrdersByProductUpc(String upc, Pageable pageable) {
        Optional<Product> p = productService.findByUpcOptional(upc);

        if (!p.isPresent()) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        return orderRepository.findAllByProductUpc(p.get().getId(), pageable);
    }

    /**
     * Add a product to an Order
     *
     * @param dto contains required data for create an order's item.
     * @return ResponseEntity  which contains updated Order.
     * @throws Exception if error.
     */
    @Override
    @Transactional
    public Order addItem(String id, ItemDTO dto) throws Exception {

        Order o = findById(id);

        if (!OrderStatus.OPEN.equals(o.getStatus())) {
            throw new ApiException("It only allows to add an item to an order if the order's status is OPEN. Order status: " + o.getStatus());
        }

        Product p = productService.findByUpc(dto.getUpc());

        Integer inventoryCount = productService.getInventoryCount(p.getId(), o.getStoreId());

        if (inventoryCount < dto.getQuantity()) {
            throw new ApiException("The requested quantity is exceeded the number of available products in this store. Requested: " + dto.getQuantity() + ". Available: " + inventoryCount);
        }

        Item item = o.getItems().getOrDefault(p.getId(), new Item(p.getId(), p.getName(), 0, p.getPrice()));

        item.increaseQuantity(dto.getQuantity());

        o.getItems().put(item.getProductId(), item);

        calcOrderAmount(o);

        o = orderRepository.save(o);
        productService.decreaseInventory(p.getId(), o.getStoreId(), dto.getQuantity());

        return o;
    }

    /**
     * Remove a product out of an order
     *
     * @param id  The id of the order
     * @param pid The id of the product to be removed
     * @return ResponseEntity  which contains updated Order.
     * @throws Exception if error.
     */
    @Override
    @Transactional
    public Order removeItem(String id, String pid) throws Exception {
        Order o = findById(id);

        if (!OrderStatus.OPEN.equals(o.getStatus())) {
            throw new ApiException("It only allows to remove an item from an order if the order's status is OPEN. Order status: " + o.getStatus());
        }

        if (!o.getItems().containsKey(pid)) {
            throw new ApiException("The provided product is not available in this order");
        }

        productService.increaseInventory(pid, o.getStoreId(), o.getItems().get(pid).getQuantity());

        o.getItems().remove(pid);
        calcOrderAmount(o);

        o = orderRepository.save(o);

        return o;
    }

    @Override
    @Transactional
    public Order confirm(String id) throws Exception {
        Order o = findById(id);

        if (!OrderStatus.OPEN.equals(o.getStatus())) {
            throw new ApiException("It only allows to confirm an order if its status is OPEN. Order status: " + o.getStatus());
        }

        if (o.getItems().size() == 0) {
            throw new ApiException("It only allows to confirm an order if it contains at least one item");
        }

        calcOrderAmount(o);
        applyPromotion(o);

        o.setStatus(OrderStatus.CONFIRMED);
        return orderRepository.save(o);
    }

    @Override
    @Transactional
    public Order addPayment(String id, PaymentDTO dto) throws Exception {
        Order o = findById(id);

        if (!OrderStatus.CONFIRMED.equals(o.getStatus())) {
            throw new ApiException("It only allows to add a payment to an order if the order's status is CONFIRMED. Order Status:" + o.getStatus());
        }

        BigDecimal paid = o.getPayments().values()
                .parallelStream()
                .map(Payment::getAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(dto.getAmount());

        if (paid.compareTo(o.getTotalAmount()) > 0) {
            throw new ApiException("The payment amount is exceeded the total amount of this order. Amount due: " + o.getTotalAmount().subtract(paid.subtract(dto.getAmount())));
        }

        Payment p = new Payment(dto.getPaymentMethod(), dto.getAmount());

        o.getPayments().put(p.getId(), p);

        return orderRepository.save(o);
    }


    /**
     * Remove a payment from order
     *
     * @param id  The id of the order
     * @param pid the id of the payment to be removed
     * @return the updated Order.
     * @throws Exception if error.
     */
    @Override
    @Transactional
    public Order removePayment(String id, String pid) throws Exception {
        Order o = findById(id);

        if (!OrderStatus.CONFIRMED.equals(o.getStatus())) {
            throw new ApiException("It only allows to remove a payment from an order if the order's status is CONFIRMED. Order Status:" + o.getStatus());
        }

        if (!o.getPayments().containsKey(pid)) {
            throw new ApiException("The provided payment id is not exist in this order. Payment id: " + pid);
        }

        o.getPayments().remove(pid);

        return orderRepository.save(o);
    }

    /**
     * Close an Order
     *
     * @param id The id of the order
     * @return the updated Order.
     * @throws Exception if error.
     */
    @Override
    @Transactional
    public Order close(String id) throws Exception {
        Order o = findById(id);

        if (!OrderStatus.CONFIRMED.equals(o.getStatus())) {
            throw new ApiException("It is only allowed to closed an order if its status is CONFIRMED. Order Status:" + o.getStatus());
        }

        BigDecimal paid = o.getPayments().values()
                .parallelStream()
                .map(Payment::getAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        if (paid.compareTo(o.getTotalAmount()) != 0) {
            throw new ApiException("Can not close the order because it is not fully paid. Amount due:" + o.getTotalAmount().subtract(paid));
        }

        o.setStatus(OrderStatus.COMPLETED);

        /**
         * Publish kafka event to produce the order message to kafka server.
         * Kafka consumer will process the order message to:
         * 1. Aggregate report data
         */
        eventPublisher.multicastEvent(new KafkaMessageProduceEvent(ORDER_TOPIC, o.getId(), ObjectMapper.instance().writeValueAsString(o)));

        return orderRepository.save(o);
    }

    /**
     * Cancel an order
     *
     * @param id The id of the order
     * @return the updated Order.
     * @throws Exception if error.
     */
    @Override
    public Order cancel(String id) throws Exception {
        Order o = findById(id);

        if (OrderStatus.COMPLETED.equals(o.getStatus()) || OrderStatus.CANCELED.equals(o.getStatus())) {
            throw new ApiException("It only allows to cancel an order if its status is not COMPLETED and CANCELED. Order Status: " + o.getStatus());
        }

        o.setStatus(OrderStatus.CANCELED);

        /**
         * Publish kafka event to produce the order message to kafka server.
         * Kafka consumer will process the order message to:
         * 1. Revert the inventory if any
         * 2. Issue refund to customer if any
         */
        eventPublisher.multicastEvent(new KafkaMessageProduceEvent(ORDER_TOPIC, o.getId(), ObjectMapper.instance().writeValueAsString(o)));

        return orderRepository.save(o);
    }

    /**
     * Get all purchased products in an order
     *
     * @param id The id of the order
     * @return Collection of order's items
     * @throws Exception if error.
     */
    @Override
    public Collection<Item> getAllProductsInOrder(String id) throws Exception {
        return findById(id).getItems().values();
    }

    /**
     * Get all payments in an order
     *
     * @param id The id of the order
     * @return Collection of order's payment
     * @throws Exception if error.
     */
    @Override
    public Collection<Payment> getPaymentsInOrder(String id) throws Exception {
        return findById(id).getPayments().values();
    }

    /**
     * Calculate the order total amount based on purchased amount and tax
     *
     * @param o the order object
     */
    private void calcOrderAmount(Order o) {
        o.setTaxPercent(TAX_PERCENT);

        o.setSubAmount(o.getItems()
                .values()
                .parallelStream()
                .map(Item::getAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO));


        o.setTaxAmount(o.getSubAmount().multiply(o.getTaxPercent()));
        o.setTotalAmount(o.getSubAmount().subtract(o.getDiscountAmount()).add(o.getTaxAmount()));

    }

    /**
     * Apply any available promotion to the order
     *
     * @param o the order object
     */
    private void applyPromotion(Order o) {
        //TODO: Implement promotion apply logic
    }

}
