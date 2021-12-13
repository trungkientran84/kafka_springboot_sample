package com.t.t.k.ims.service.products;


import com.t.t.k.ims.common.utils.ModelMapper;
import com.t.t.k.ims.common.utils.ObjectMapper;
import com.t.t.k.ims.controller.products.dto.InventoryDTO;
import com.t.t.k.ims.controller.products.dto.ProductDTO;
import com.t.t.k.ims.kafka.KafkaMessageProduceEvent;
import com.t.t.k.ims.model.products.Inventory;
import com.t.t.k.ims.model.products.Product;
import com.t.t.k.ims.repository.products.InventoryRepository;
import com.t.t.k.ims.repository.products.ProductRepository;
import com.t.t.k.ims.validation.error.Violation;
import com.t.t.k.ims.validation.exception.InvalidParameterException;
import com.t.t.k.ims.validation.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * This class implement all business methods available on products
 *
 * @author ttkien
 */

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final InventoryRepository inventoryRepository;

    private final ApplicationEventMulticaster eventPublisher;

    @Value("${app.config.kafka.topics.inventory}")
    private String INVENTORY_TOPIC;

    /**
     * The constructor to create instance of this class
     *
     * @param productRepository   the dependent repository
     * @param inventoryRepository the dependent repository
     * @param eventPublisher      the dependent even publisher
     */
    public ProductServiceImpl(ProductRepository productRepository, InventoryRepository inventoryRepository, ApplicationEventMulticaster eventPublisher) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * Add a new product
     *
     * @param dto the object contains request data
     * @return created merchant store
     * @throws InvalidParameterException if error
     */
    @Override
    public Product addProduct(ProductDTO dto) throws Exception {

        if (productRepository.findByUpc(dto.getUpc()).isPresent()) {
            InvalidParameterException ex = new InvalidParameterException("Invalid data");
            ex.getViolations().add(new Violation("Upc", "The provided upc number is used by another product", dto.getUpc(), "Product"));
            throw ex;
        }

        Product c = new Product();

        ModelMapper.intance().map(dto, c);

        return productRepository.save(c);

    }

    /**
     * Update a product by id
     *
     * @param id  the product id to update
     * @param dto the object contains request data
     * @return updated product model
     * @throws InvalidParameterException if there is any error
     */
    @Override
    @Transactional
    public Product updateProduct(String id, ProductDTO dto) throws Exception {

        Product c = productRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Product", id));

        if (!c.getUpc().equals(dto.getUpc()) && productRepository.findByUpc(dto.getUpc()).isPresent()) {
            InvalidParameterException ex = new InvalidParameterException("Invalid data");
            ex.getViolations().add(new Violation("Upc", "he provided upc number is used by another product", dto.getUpc(), "Product"));
            throw ex;
        }

        ModelMapper.intance().map(dto, c);

        return productRepository.save(c);

    }

    /**
     * Delete a product by id
     *
     * @param id the product id to delete
     */
    @Override
    public void deleteProduct(String id) {
        //TODO: validate product being used in inventory, order, and promotion
        productRepository.deleteById(id);
    }

    /**
     * Find a product by id.
     *
     * @param id the id of target product
     * @return the product data model
     * @throws Exception if there is any error
     */
    @Override
    public Product findById(String id) throws Exception {
        return productRepository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Product", id));
    }

    /**
     * Find a product by upc number.
     *
     * @param upc the upc number of target product
     * @return the product data model
     * @throws Exception if there is any error
     */
    @Override
    public Product findByUpc(String upc) throws Exception {
        return findByUpcOptional(upc).orElseThrow(()
                -> new ObjectNotFoundException("Product", upc));
    }

    /**
     * Find a product by upc number that return the Optional object.
     *
     * @param upc the upc number of target product
     * @return the optional of the product
     */
    @Override
    public Optional<Product> findByUpcOptional(String upc) {
        return productRepository.findByUpc(upc);
    }

    /**
     * Get all products.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of products
     */
    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Increase product inventory count
     *
     * @param id  the product id to update
     * @param dto the amount to increase
     * @return updated product model
     * @throws Exception if there is any error
     */
    @Override
    @Transactional
    public Inventory increaseInventory(String id, InventoryDTO dto) throws Exception {

        Product p = productRepository.findById(id).orElseThrow(()
                -> new ObjectNotFoundException("Product", id));

        Optional<Inventory> i = inventoryRepository.findByProductIdAndStoreId(p.getId(), dto.getStoreId());
        Inventory j = new Inventory();
        if (i.isPresent()) {
            j = i.get();
            j.increase(dto.getCount());
        } else {
            ModelMapper.intance().map(dto, j);
            j.setProductId(id);
        }

        return inventoryRepository.save(j);
    }

    /**
     * Increase product inventory count
     *
     * @param id       the product id to update
     * @param storeId  the target store to update inventory
     * @param quantity the amount to increase
     * @return updated product model
     * @throws Exception if there is any error
     */
    public Inventory increaseInventory(String id, String storeId, Integer quantity) throws Exception {
        return increaseInventory(id, new InventoryDTO(storeId, quantity));
    }


    /**
     * decrease product inventory count
     *
     * @param id       the product id to update
     * @param storeId  the target store to update inventory
     * @param quantity the amount to decrease
     * @return updated product model
     * @throws InvalidParameterException if there is any error
     */
    @Override
    @Transactional
    public Inventory decreaseInventory(String id, String storeId, Integer quantity) throws Exception {

        Assert.notNull(quantity, "The quantity must not be null");

        Inventory i = inventoryRepository.findByProductIdAndStoreId(id, storeId).orElseThrow(()
                -> new ObjectNotFoundException("There is no inventory record for this store ", storeId));

        if (quantity > i.getCount()) {
            InvalidParameterException ex = new InvalidParameterException("Invalid data");
            ex.getViolations().add(new Violation("amount", "The provided amount is exceeded the available inventory count of the product in this store. Inventory count:" + i.getCount(), quantity.toString(), "Product"));
            throw ex;
        }

        i.decrease(quantity);

        /**
         * Publish kafka event to produce the inventory change message to kafka server.
         * Kafka consumer will process the message to make inventory threshold alert if it is necessary
         */
        eventPublisher.multicastEvent(new KafkaMessageProduceEvent(INVENTORY_TOPIC, i.getId(), ObjectMapper.instance().writeValueAsString(i)));

        return inventoryRepository.save(i);
    }

    /**
     * Get inventory of a product.
     *
     * @param pid      the product id
     * @param pageable The object contains pageable information
     * @return the pageable list of products
     * @throws Exception if error
     */
    @Override
    public Page<Inventory> getInventory(String pid, Pageable pageable) throws Exception {
        if (!productRepository.existsById(pid)) throw new ObjectNotFoundException("Product", pid);

        return inventoryRepository.findAllByProductId(pid, pageable);
    }

    /**
     * Get inventory of a product.
     *
     * @param id  the product id
     * @param sid the store id
     * @return the Inventory count
     * @throws Exception if error
     */
    @Override
    public Integer getInventoryCount(String id, String sid) throws Exception {
        return inventoryRepository.findByProductIdAndStoreId(id, sid)
                .orElseThrow(() -> new ObjectNotFoundException("The product is not available in this store"))
                .getCount();
    }

}
