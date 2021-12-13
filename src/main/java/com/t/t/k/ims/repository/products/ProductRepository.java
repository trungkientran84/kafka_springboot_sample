package com.t.t.k.ims.repository.products;

import com.t.t.k.ims.model.products.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * This interface contains the database related methods for products.
 *
 * @author ttkien
 */

public interface ProductRepository extends MongoRepository<Product, String> {

    /**
     * Find the product by upc number
     * @param s
     * @return
     */
    Optional<Product> findByUpc(String s);
}
