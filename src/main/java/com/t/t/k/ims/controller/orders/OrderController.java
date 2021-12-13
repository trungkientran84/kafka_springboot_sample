package com.t.t.k.ims.controller.orders;

import com.t.t.k.ims.controller.orders.dto.ItemDTO;
import com.t.t.k.ims.controller.orders.dto.OrderDTO;
import com.t.t.k.ims.controller.orders.dto.PaymentDTO;
import com.t.t.k.ims.model.orders.Item;
import com.t.t.k.ims.model.orders.Order;
import com.t.t.k.ims.model.orders.Payment;
import com.t.t.k.ims.service.orders.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * This controller handles endpoints all end points related to order
 *
 * @author ttkien
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create an Order.
     *
     * @param dto contains request data for create new order.
     * @return ResponseEntity  which contains created Order.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Create a Order",
            description = "It creates and returns a new Order.\n\n" +
                    "@param: OrderDTO object in request body contains the data for create a new order.\n\n" +
                    "@return: the created Order\n\n")
    @PostMapping()
    public ResponseEntity<Order> addOrder(@Valid @RequestBody OrderDTO dto) throws Exception {
        return new ResponseEntity<>(orderService.addOrder(dto), HttpStatus.CREATED);
    }

    /**
     * Delete a Order by its id.
     *
     * @param id the id of order.
     * @return ResponseEntity with OK status
     * @throws Exception if the there is an error of deletion
     */
    @Operation(
            summary = "Delete a Order by its id.",
            description = "It deletes a Order by given id\n\n" +
                    "@return ResponseEntity with OK status\n\n")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String id) throws Exception {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * find a order by its id.
     *
     * @param id the id of order.
     * @return ResponseEntity object with found order.
     * @throws Exception ObjectNotFoundException
     */
    @Operation(
            summary = "Find a order by its id.",
            description = "It returns a order found by the given id.\n\n" +
                    "@param id the id of order." +
                    "@return ResponseEntity object with found order\n\n")
    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    /**
     * Get all orders in the system.
     *
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list of orders.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all orders in the system",
            description = "It returns all orders in the system with the paging information.\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of orders.\n\n")
    @GetMapping()
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) throws Exception {
        return new ResponseEntity<>(orderService.findAllOrders(pageable), HttpStatus.OK);
    }

    /**
     * In recall situation, find all orders that contains the product of given upc number
     *
     * @param upc      the upc number of a product
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list of orders.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Product Recall: find all orders that contains the product of given upc number",
            description = "It returns all orders that contains the product of given upc number along with the paging information.\n\n" +
                    "@param upc the upc number of a product\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of orders.\n\n")
    @GetMapping("/product/{upc}")
    public ResponseEntity<Page<Order>> findAllOrderByProductUpc(@PathVariable String upc, Pageable pageable) throws Exception {
        return new ResponseEntity<>(orderService.findAllOrdersByProductUpc(upc, pageable), HttpStatus.OK);
    }

    /**
     * Get all purchased products in an order.
     *
     * @param id the order id
     * @return ResponseEntity with status OK and contains a collection of purchased items.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all purchased products in an order.",
            description = "It returns all purchased items in an order.\n\n" +
                    "@param id the order id\n\n" +
                    "@return ResponseEntity with status OK and contains a collection of purchased items.\n\n")
    @GetMapping("/{id}/items")
    public ResponseEntity<Collection<Item>> getPurchasedItemsInOrder(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(orderService.getAllProductsInOrder(id), HttpStatus.OK);
    }

    /**
     * Get all payments in an order.
     *
     * @param id the order id
     * @return ResponseEntity with status OK and contains a collection of payments.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all payments in an order.",
            description = "It returns all payments in an order.\n\n" +
                    "@param id the order id\n\n" +
                    "@return ResponseEntity with status OK and contains a collection of purchased items.\n\n")
    @GetMapping("/{id}/payments")
    public ResponseEntity<Collection<Payment>> getPaymentsInOrder(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(orderService.getPaymentsInOrder(id), HttpStatus.OK);
    }


    /**
     * Add a product to an Order
     *
     * @param id  the order id
     * @param dto contains required data for create an order's item.
     * @return ResponseEntity  which contains updated Order.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Add a product to an Order",
            description = "It adds an item to the order with given data and returns the updated Order.\n\n" +
                    "@param: id the order id\n\n" +
                    "@param: dto object in request body contains the data for create a new item.\n\n" +
                    "@return: the updated Order\n\n")
    @PostMapping("/{id}/items")
    public ResponseEntity<Order> addProductToOrder(@PathVariable String id, @Valid @RequestBody ItemDTO dto) throws Exception {
        return new ResponseEntity<>(orderService.addItem(id, dto), HttpStatus.CREATED);
    }

    /**
     * Remove a product from an Order
     *
     * @param id  the order id
     * @param pid the product id
     * @return ResponseEntity which contains updated Order.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Add a product to an Order",
            description = "It removes an item from the order with given product id and returns the updated Order.\n\n" +
                    "@param: id the order id\n\n" +
                    "@param: pid the product id to be removed from the order.\n\n" +
                    "@return: the updated Order\n\n")
    @DeleteMapping("/{id}/items/{pid}")
    public ResponseEntity<Order> removeProductFromOrder(@PathVariable String id, @PathVariable String pid) throws Exception {
        return new ResponseEntity<>(orderService.removeItem(id, pid), HttpStatus.CREATED);
    }

    /**
     * Confirm an order and make it ready to getting paid
     *
     * @param id the order id
     * @return ResponseEntity which contains the updated order.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Confirm an order and make it ready to getting paid",
            description = "It confirms an order to be ready for getting paid and returns the updated order.\n\n" +
                    "@param: id  the order id\n\n" +
                    "@return: the updated order\n\n")
    @PostMapping("/{id}/confirm")
    public ResponseEntity<Order> confirmOrder(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(orderService.confirm(id), HttpStatus.CREATED);
    }

    /**
     * Add a payment to an Order
     *
     * @param id  the order id
     * @param dto contains required data for create an order's payment.
     * @return ResponseEntity  which contains updated Order.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Add a payment to an Order",
            description = "It adds an item to the order with given data and returns the updated Order.\n\n" +
                    "@param: id  the order id\n\n" +
                    "@param: dto object in request body contains the data for create a payment.\n\n" +
                    "@return: the updated Order\n\n")
    @PostMapping("/{id}/payments")
    public ResponseEntity<Order> addPaymenttToOrder(@PathVariable String id, @Valid @RequestBody PaymentDTO dto) throws Exception {
        return new ResponseEntity<>(orderService.addPayment(id, dto), HttpStatus.CREATED);
    }

    /**
     * Remove a payment from an Order
     *
     * @param id  the order id
     * @param pid the payment id
     * @return ResponseEntity which contains updated Order.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Remove a payment from an Order",
            description = "It removes a payment from the order with given payment id and returns the updated Order.\n\n" +
                    "@param: id the order id\n\n" +
                    "@param: pid the payment id to be removed from the order.\n\n" +
                    "@return: the updated Order\n\n")
    @DeleteMapping("/{id}/payments/{pid}")
    public ResponseEntity<Order> removePaymentFromOrder(@PathVariable String id, @PathVariable String pid) throws Exception {
        return new ResponseEntity<>(orderService.removePayment(id, pid), HttpStatus.CREATED);
    }

    /**
     * Close an order
     *
     * @param id the order id
     * @return ResponseEntity which contains the updated order.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Close an order",
            description = "It closes an order by update order status to COMPLETE and returns the updated order.\n\n" +
                    "@param: id  the order id\n\n" +
                    "@return: the updated order\n\n")
    @PostMapping("/{id}/close")
    public ResponseEntity<Order> closeOrder(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(orderService.close(id), HttpStatus.CREATED);
    }

    /**
     * Cancel an order
     *
     * @param id the order id
     * @return ResponseEntity which contains the updated order.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Cancel an order",
            description = "It cancels an order by update order status to CANCELED and returns the updated order.\n\n" +
                    "@param: id  the order id\n\n" +
                    "@return: the updated order\n\n")
    @PostMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(orderService.cancel(id), HttpStatus.CREATED);
    }
}
