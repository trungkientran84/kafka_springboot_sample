package com.t.t.k.ims.service.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.controller.products.dto.ProductDTO;
import com.t.t.k.ims.model.products.Inventory;
import com.t.t.k.ims.model.products.Product;
import com.t.t.k.ims.repository.products.InventoryRepository;
import com.t.t.k.ims.repository.products.ProductRepository;
import com.t.t.k.ims.validation.exception.InvalidParameterException;
import com.t.t.k.ims.validation.exception.ObjectNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@DirtiesContext
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
class ProductServiceImplTest {
    @MockBean
    private InventoryRepository inventoryRepository;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Test
    void testConstructor() {

        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);

        // Act
        new ProductServiceImpl(productRepository, inventoryRepository, new SimpleApplicationEventMulticaster());

    }

    @Test
    void testAddProduct() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        Optional<Product> ofResult = Optional.<Product>of(product);
        when(this.productRepository.findByUpc((String) any())).thenReturn(ofResult);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act and Assert
        assertThrows(InvalidParameterException.class, () -> this.productServiceImpl.addProduct(productDTO));
        verify(this.productRepository).findByUpc((String) any());
    }

    @Test
    void testAddProduct2() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.productRepository.findByUpc((String) any())).thenReturn(Optional.<Product>empty());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act
        Product actualAddProductResult = this.productServiceImpl.addProduct(productDTO);

        // Assert
        assertSame(product, actualAddProductResult);
        assertEquals("1", actualAddProductResult.getPrice().toString());
        verify(this.productRepository).findByUpc((String) any());
        verify(this.productRepository).save((Product) any());
    }

    @Test
    void testAddProduct3() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.productRepository.findByUpc((String) any())).thenReturn(Optional.<Product>empty());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(null);
        productDTO.setUpc("Upc");

        // Act
        Product actualAddProductResult = this.productServiceImpl.addProduct(productDTO);

        // Assert
        assertSame(product, actualAddProductResult);
        assertEquals("1", actualAddProductResult.getPrice().toString());
        verify(this.productRepository).findByUpc((String) any());
        verify(this.productRepository).save((Product) any());
    }

    @Test
    void testUpdateProduct() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        Optional<Product> ofResult = Optional.<Product>of(product);

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");
        when(this.productRepository.save((Product) any())).thenReturn(product1);
        when(this.productRepository.findById((String) any())).thenReturn(ofResult);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act
        Product actualUpdateProductResult = this.productServiceImpl.updateProduct("42", productDTO);

        // Assert
        assertSame(product1, actualUpdateProductResult);
        assertEquals("1", actualUpdateProductResult.getPrice().toString());
        verify(this.productRepository).findById((String) any());
        verify(this.productRepository).save((Product) any());
    }

    @Test
    void testUpdateProduct2() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(null);
        product.setUpc("Upc");
        Optional<Product> ofResult = Optional.<Product>of(product);

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");
        when(this.productRepository.save((Product) any())).thenReturn(product1);
        when(this.productRepository.findById((String) any())).thenReturn(ofResult);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act
        Product actualUpdateProductResult = this.productServiceImpl.updateProduct("42", productDTO);

        // Assert
        assertSame(product1, actualUpdateProductResult);
        assertEquals("1", actualUpdateProductResult.getPrice().toString());
        verify(this.productRepository).findById((String) any());
        verify(this.productRepository).save((Product) any());
    }

    @Test
    void testUpdateProduct3() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("com.t.t.k.ims.model.products.Product");
        Optional<Product> ofResult = Optional.<Product>of(product);

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        Product product2 = new Product();
        product2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product2.setId("42");
        product2.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product2.setInventoryThreshold(1);
        product2.setName("Name");
        product2.setPrice(BigDecimal.valueOf(1L));
        product2.setUpc("Upc");
        Optional<Product> ofResult1 = Optional.<Product>of(product2);
        when(this.productRepository.findByUpc((String) any())).thenReturn(ofResult1);
        when(this.productRepository.save((Product) any())).thenReturn(product1);
        when(this.productRepository.findById((String) any())).thenReturn(ofResult);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act and Assert
        assertThrows(InvalidParameterException.class, () -> this.productServiceImpl.updateProduct("42", productDTO));
        verify(this.productRepository).findById((String) any());
        verify(this.productRepository).findByUpc((String) any());
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        doNothing().when(this.productRepository).deleteById((String) any());

        // Act
        this.productServiceImpl.deleteProduct("42");

        // Assert
        verify(this.productRepository).deleteById((String) any());
    }

    @Test
    void testFindById() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        Optional<Product> ofResult = Optional.<Product>of(product);
        when(this.productRepository.findById((String) any())).thenReturn(ofResult);

        // Act
        Product actualFindByIdResult = this.productServiceImpl.findById("42");

        // Assert
        assertSame(product, actualFindByIdResult);
        assertEquals("1", actualFindByIdResult.getPrice().toString());
        verify(this.productRepository).findById((String) any());
    }

    @Test
    void testFindById2() throws Exception {
        // Arrange
        when(this.productRepository.findById((String) any())).thenReturn(Optional.<Product>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.productServiceImpl.findById("42"));
        verify(this.productRepository).findById((String) any());
    }

    @Test
    void testFindByUpc() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        Optional<Product> ofResult = Optional.<Product>of(product);
        when(this.productRepository.findByUpc((String) any())).thenReturn(ofResult);

        // Act
        Product actualFindByUpcResult = this.productServiceImpl.findByUpc("Upc");

        // Assert
        assertSame(product, actualFindByUpcResult);
        assertEquals("1", actualFindByUpcResult.getPrice().toString());
        verify(this.productRepository).findByUpc((String) any());
    }

    @Test
    void testFindByUpc2() throws Exception {
        // Arrange
        when(this.productRepository.findByUpc((String) any())).thenReturn(Optional.<Product>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.productServiceImpl.findByUpc("Upc"));
        verify(this.productRepository).findByUpc((String) any());
    }

    @Test
    void testFindByUpcOptional() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        Optional<Product> ofResult = Optional.<Product>of(product);
        when(this.productRepository.findByUpc((String) any())).thenReturn(ofResult);

        // Act
        Optional<Product> actualFindByUpcOptionalResult = this.productServiceImpl.findByUpcOptional("Upc");

        // Assert
        assertSame(ofResult, actualFindByUpcOptionalResult);
        assertTrue(actualFindByUpcOptionalResult.isPresent());
        assertEquals("1", actualFindByUpcOptionalResult.get().getPrice().toString());
        verify(this.productRepository).findByUpc((String) any());
    }

    @Test
    void testFindAllProducts() {
        // Arrange
        PageImpl<Product> pageImpl = new PageImpl<Product>(new ArrayList<Product>());
        when(this.productRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<Product> actualFindAllProductsResult = this.productServiceImpl.findAllProducts(null);

        // Assert
        assertSame(pageImpl, actualFindAllProductsResult);
        assertTrue(actualFindAllProductsResult.toList().isEmpty());
        verify(this.productRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testDecreaseInventory() throws Exception {
        // Arrange
        when(this.inventoryRepository.findByProductIdAndStoreId((String) any(), (String) any()))
                .thenReturn(Optional.<Inventory>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.productServiceImpl.decreaseInventory("42", "42", 1));
        verify(this.inventoryRepository).findByProductIdAndStoreId((String) any(), (String) any());
    }

    @Test
    void testGetInventory() throws Exception {
        // Arrange
        PageImpl<Inventory> pageImpl = new PageImpl<Inventory>(new ArrayList<Inventory>());
        when(this.inventoryRepository.findAllByProductId((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(pageImpl);
        when(this.productRepository.existsById((String) any())).thenReturn(true);

        // Act
        Page<Inventory> actualInventory = this.productServiceImpl.getInventory("Pid", null);

        // Assert
        assertSame(pageImpl, actualInventory);
        assertTrue(actualInventory.toList().isEmpty());
        verify(this.inventoryRepository).findAllByProductId((String) any(),
                (org.springframework.data.domain.Pageable) any());
        verify(this.productRepository).existsById((String) any());
    }

    @Test
    void testGetInventory2() throws Exception {
        // Arrange
        when(this.inventoryRepository.findAllByProductId((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Inventory>(new ArrayList<Inventory>()));
        when(this.productRepository.existsById((String) any())).thenReturn(false);

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.productServiceImpl.getInventory("Pid", null));
        verify(this.productRepository).existsById((String) any());
    }

    @Test
    void testGetInventoryCount() throws Exception {
        // Arrange
        when(this.inventoryRepository.findByProductIdAndStoreId((String) any(), (String) any()))
                .thenReturn(Optional.<Inventory>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.productServiceImpl.getInventoryCount("42", "Sid"));
        verify(this.inventoryRepository).findByProductIdAndStoreId((String) any(), (String) any());
    }
}

