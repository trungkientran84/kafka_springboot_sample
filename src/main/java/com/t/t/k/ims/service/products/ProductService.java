package com.t.t.k.ims.service.products;

import com.t.t.k.ims.controller.products.dto.InventoryDTO;
import com.t.t.k.ims.controller.products.dto.ProductDTO;
import com.t.t.k.ims.model.products.Inventory;
import com.t.t.k.ims.model.products.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * This interface defines all business methods available on products
 *
 * @author ttkien
 */
public interface ProductService {


    /**
     * Add a new product
     *
     * @param dto the object contains request data
     * @return created product
     * @throws Exception if there is any error
     */
    Product addProduct(ProductDTO dto) throws Exception;

    /**
     * Update a product by id
     *
     * @param id  the product id to update
     * @param dto the object contains request data
     * @return updated product model
     * @throws Exception if there is any error
     */
    Product updateProduct(String id, ProductDTO dto) throws Exception;


    /**
     * Delete a product by id
     *
     * @param id the product id to delete
     * @throws Exception if there is any error
     */
    void deleteProduct(String id) throws Exception;

    /**
     * Find a product by id.
     *
     * @param id the id of target product
     * @return the product data model
     * @throws Exception if there is any error
     */
    Product findById(String id) throws Exception;

    /**
     * Find a product by upc number.
     *
     * @param upc the upc number of target product
     * @return the product data model
     * @throws Exception if there is any error
     */
    Product findByUpc(String upc) throws Exception;

    /**
     * Find a product by upc number that return the Optional object.
     *
     * @param upc the upc number of target product
     * @return the optional of the product
     */
    Optional<Product> findByUpcOptional(String upc);


    /**
     * Get all products.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of products
     */
    Page<Product> findAllProducts(Pageable pageable) throws Exception;

    /**
     * Increase product inventory count
     *
     * @param id  the product id to update
     * @param dto the amount to increase
     * @return updated product model
     * @throws Exception if there is any error
     */
    Inventory increaseInventory(String id, InventoryDTO dto) throws Exception;

    /**
     * Increase product inventory count
     *
     * @param id  the product id to update
     * @param storeId the target store to update inventory
     * @param quantity the quantity to increase
     * @return updated product model
     * @throws Exception if there is any error
     */
    Inventory increaseInventory(String id, String storeId, Integer quantity) throws Exception;

    /**
     * decrease product inventory count
     *
     * @param id  the product id to update
     * @param storeId the target store to update inventory
     * @param quantity the quantity to decrease
     * @return updated product model
     * @throws Exception if there is any error
     */
    Inventory decreaseInventory(String id, String storeId, Integer quantity) throws Exception;

    /**
     * Get inventory of a product.
     *
     * @param pid the product id
     * @param pageable The object contains pageable information
     * @return the pageable list of products
     */
    Page<Inventory> getInventory(String pid, Pageable pageable) throws Exception;

    /**
     * Get inventory of a product.
     *
     * @param id the product id
     * @param sid the store id
     * @return the Inventory object
     * @throws Exception if error
     */
    Integer getInventoryCount(String id, String sid) throws Exception;
}
