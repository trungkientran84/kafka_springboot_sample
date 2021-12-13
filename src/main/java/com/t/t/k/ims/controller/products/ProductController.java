package com.t.t.k.ims.controller.products;

import com.t.t.k.ims.controller.products.dto.InventoryDTO;
import com.t.t.k.ims.controller.products.dto.ProductDTO;
import com.t.t.k.ims.model.products.Inventory;
import com.t.t.k.ims.model.products.Product;
import com.t.t.k.ims.service.products.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This controller handles endpoints all end points related to products
 *
 * @author ttkien
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Create a Product.
     *
     * @param dto contains request data for create new product.
     * @return ResponseEntity  which contains created Product.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Create a Product",
            description = "It creates and returns a new Product.\n\n" +
                    "@param: ProductDTO object in request body contains the data for create a new product.\n\n" +
                    "@return: the created Product\n\n")
    @PostMapping()
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductDTO dto) throws Exception {
        return new ResponseEntity<>(productService.addProduct(dto), HttpStatus.CREATED);
    }

    /**
     * Update a Product.
     *
     * @param dto contains request data for updating new product.
     * @return ResponseEntity  which contains updated Product.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Update data of a Product",
            description = "It update a product by given id and returns the updated product.\n\n" +
                    "@param: ProductDTO object in request body contains the data for updating product.\n\n" +
                    "@return: the updated Product\n\n")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @Valid @RequestBody ProductDTO dto) throws Exception {
        return new ResponseEntity<>(productService.updateProduct(id, dto), HttpStatus.OK);
    }

    /**
     * Delete a Product by its id.
     *
     * @param id the id of product.
     * @return ResponseEntity with OK status
     * @throws Exception if the there is an error of deletion
     */
    @Operation(
            summary = "Delete a Product by its id.",
            description = "It deletes a Product by given id\n\n" +
                    "@return ResponseEntity with OK status\n\n")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) throws Exception {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Find a product by given id.
     *
     * @param id the id of product.
     * @return ResponseEntity object with found product.
     * @throws Exception ObjectNotFoundException
     */
    @Operation(
            summary = "Find a product by given id.",
            description = "It returns a product found by the given id.\n\n" +
                    "@param id the id of product." +
                    "@return ResponseEntity object with found product\n\n")
    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    /**
     * Find a product by given upc number.
     *
     * @param upc the upc number of product.
     * @return ResponseEntity object with found product.
     * @throws Exception ObjectNotFoundException
     */
    @Operation(
            summary = "Find a product by given upc number.",
            description = "It returns a product found by the given upc number.\n\n" +
                    "@param id the id of product." +
                    "@return ResponseEntity object with found product\n\n")
    @GetMapping("/upc/{upc}")
    public ResponseEntity<Product> findProductByUpc(@PathVariable String upc) throws Exception {
        return new ResponseEntity<>(productService.findByUpc(upc), HttpStatus.OK);
    }

    /**
     * Get all products in the system.
     *
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list of products.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all products in the system",
            description = "It returns all products in the system with the paging information.\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of products.\n\n")
    @GetMapping()
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) throws Exception {
        return new ResponseEntity<>(productService.findAllProducts(pageable), HttpStatus.OK);
    }

    /**
     * Get all inventory records of the product.
     *
     * @param id       The product id
     * @param pageable a Pageable object contains parameters for paging
     * @return ResponseEntity with status OK and contains a pageable list of products.
     * @throws Exception for any error occurs while retrieving data
     */
    @Operation(
            summary = "Get all inventory records of the product",
            description = "It returns all inventory records of the product with the paging information.\n\n" +
                    "@param id the product id\n\n" +
                    "@param pageable a Pageable object contains parameters for paging such as size, page\n\n" +
                    "@return ResponseEntity with status OK and contains a pageable list of products.\n\n")
    @GetMapping("/{id}/inventory")
    public ResponseEntity<Page<Inventory>> getAllProducts(@PathVariable String id, Pageable pageable) throws Exception {
        return new ResponseEntity<>(productService.getInventory(id, pageable), HttpStatus.OK);
    }

    /**
     * Increase the inventory count of a product.
     *
     * @param dto contains request data for increasing amount.
     * @return ResponseEntity  which contains updated Inventory.
     * @throws Exception InvalidParameterException.
     */
    @Operation(
            summary = "Increase the inventory count of a product.",
            description = "It update a product by given id with an increasing inventory amount and returns the updated Inventory.\n\n" +
                    "@param: InventoryDTO object in request body contains request data for increasing amount.\n\n" +
                    "@return: the updated Inventory\n\n")
    @PostMapping("/{id}/inventory-increase")
    public ResponseEntity<Inventory> increaseInventory(@PathVariable String id, @Valid @RequestBody InventoryDTO dto) throws Exception {
        return new ResponseEntity<>(productService.increaseInventory(id, dto), HttpStatus.OK);
    }
}
