package com.t.t.k.ims.repository.products;

import com.t.t.k.ims.model.products.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * This interface contains the database related methods for inventorys.
 *
 * @author ttkien
 */

public interface InventoryRepository extends MongoRepository<Inventory, String> {

    /**
     * Find the inventory record by product id and store id
     *
     * @param pid the product id
     * @param pid the store id
     * @return Inventory object
     */
    Optional<Inventory> findByProductIdAndStoreId(String pid, String sid);

    /**
     * Find all inventory records by product id
     *
     * @param id the product id
     * @param pageable the pageable object
     * @return A pageable list of inventories
     */
    Page<Inventory> findAllByProductId(String id, Pageable pageable);
}
