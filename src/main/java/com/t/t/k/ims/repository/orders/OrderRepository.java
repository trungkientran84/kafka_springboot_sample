package com.t.t.k.ims.repository.orders;

import com.t.t.k.ims.model.orders.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * This interface contains the database query methods for the order.
 *
 * @author ttkien
 */

public interface OrderRepository extends MongoRepository<Order, String> {

    /**
     * Find all orders that its item contains the provided product key
     * @param key
     * @return
     */
    @Query("{'items.?0' : {$exists: true} }")
    Page<Order> findAllByProductUpc(String key, Pageable pageable);
}
