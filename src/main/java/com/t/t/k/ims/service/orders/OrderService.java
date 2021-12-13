package com.t.t.k.ims.service.orders;

import com.t.t.k.ims.controller.orders.dto.ItemDTO;
import com.t.t.k.ims.controller.orders.dto.OrderDTO;
import com.t.t.k.ims.controller.orders.dto.PaymentDTO;
import com.t.t.k.ims.model.orders.Item;
import com.t.t.k.ims.model.orders.Order;
import com.t.t.k.ims.model.orders.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

/**
 * This interface defines all business methods available on Order
 *
 * @author ttkien
 */
public interface OrderService {


    /**
     * Add a new order
     *
     * @param dto the object contains request data
     * @return created order
     * @throws Exception if there is any error
     */
    Order addOrder(OrderDTO dto) throws Exception;


    /**
     * Delete a order by id
     *
     * @param id the order id to delete
     * @throws Exception if there is any error
     */
    void deleteOrder(String id) throws Exception;

    /**
     * Find a order by id.
     *
     * @param id the id of target order
     * @return the order data model
     * @throws Exception if there is any error
     */
    Order findById(String id) throws Exception;


    /**
     * Get all orders.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of orders
     */
    Page<Order> findAllOrders(Pageable pageable) throws Exception;

    /**
     * find all orders by provided product upc number.
     *
     * @param upc      the product upc number
     * @param pageable The object contains pageable information
     * @return the pageable list of orders
     */
    Page<Order> findAllOrdersByProductUpc(String upc, Pageable pageable) throws Exception;

    /**
     * Get all purchased products in an order
     *
     * @param id The id of the order
     * @return Collection of order's items
     * @throws Exception if error.
     */
    Collection<Item> getAllProductsInOrder(String id) throws Exception;

    /**
     * Get all payments in an order
     *
     * @param id The id of the order
     * @return Collection of order's payment
     * @throws Exception if error.
     */
    Collection<Payment> getPaymentsInOrder(String id) throws Exception;

    /**
     * Add a product to an Order
     *
     * @param id  The id of the order
     * @param dto contains required data for create an order's item.
     * @return the updated Order.
     * @throws Exception if error.
     */
    Order addItem(String id, ItemDTO dto) throws Exception;

    /**
     * Remove a product out of an order
     *
     * @param id  The id of the order
     * @param pid The id of the product to be removed
     * @return the updated Order.
     * @throws Exception if error.
     */
    Order removeItem(String id, String pid) throws Exception;

    /**
     * Confirm an order is ready to get paid by change its status to CONFIRM
     *
     * @param id The id of the order
     * @return the updated Order.
     * @throws Exception if error.
     */
    Order confirm(String id) throws Exception;

    /**
     * Add a payment to order
     *
     * @param id  The id of the order
     * @param dto the object contains payment detail
     * @return the updated Order.
     * @throws Exception if error.
     */
    Order addPayment(String id, PaymentDTO dto) throws Exception;

    /**
     * Remove a payment from order
     *
     * @param id  The id of the order
     * @param pid the id of the payment to be removed
     * @return the updated Order.
     * @throws Exception if error.
     */
    Order removePayment(String id, String pid) throws Exception;


    /**
     * Close an Order
     *
     * @param id The id of the order
     * @return the updated Order.
     * @throws Exception if error.
     */
    Order close(String id) throws Exception;

    /**
     * Cancel an order
     *
     * @param id The id of the order
     * @return the updated Order.
     * @throws Exception if error.
     */
    Order cancel(String id) throws Exception;


}
