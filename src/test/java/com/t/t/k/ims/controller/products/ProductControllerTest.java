package com.t.t.k.ims.controller.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t.t.k.ims.controller.products.dto.ProductDTO;
import com.t.t.k.ims.model.products.Product;
import com.t.t.k.ims.repository.products.InventoryRepository;
import com.t.t.k.ims.repository.products.ProductRepository;
import com.t.t.k.ims.service.products.ProductService;
import com.t.t.k.ims.service.products.ProductServiceImpl;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
class ProductControllerTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductServiceImpl productServiceImpl;

    @Test
    void testConstructor() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productServiceImpl = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act and Assert
        ProductService productService = (new ProductController(productServiceImpl)).productService;
        assertTrue(productService instanceof ProductServiceImpl);
        assertSame(productService, productServiceImpl);
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
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.save((Product) any())).thenReturn(product);
        when(productRepository.findByUpc((String) any())).thenReturn(Optional.<Product>empty());
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductController productController = new ProductController(
                new ProductServiceImpl(productRepository, inventoryRepository, new SimpleApplicationEventMulticaster()));

        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act
        ResponseEntity<Product> actualAddProductResult = productController.addProduct(productDTO);

        // Assert
        assertEquals("<201 CREATED Created,Product(id=42, name=Name, upc=Upc, price=1, inventoryThreshold=1),[]>",
                actualAddProductResult.toString());
        assertTrue(actualAddProductResult.getHeaders().isEmpty());
        assertTrue(actualAddProductResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualAddProductResult.getStatusCode());
        assertEquals("1", actualAddProductResult.getBody().getPrice().toString());
        verify(productRepository).findByUpc((String) any());
        verify(productRepository).save((Product) any());
    }

    @Test
    void testDeleteProduct() throws Exception {
        // Arrange
        doNothing().when(this.productServiceImpl).deleteProduct((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteProduct2() throws Exception {
        // Arrange
        doNothing().when(this.productServiceImpl).deleteProduct((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/products/{id}", "42");
        deleteResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllProducts2() throws Exception {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Product>(new ArrayList<Product>()));
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);

        // Act
        ResponseEntity<Page<Product>> actualAllProducts = (new ProductController(
                new ProductServiceImpl(productRepository, inventoryRepository, new SimpleApplicationEventMulticaster())))
                .getAllProducts(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualAllProducts.toString());
        assertTrue(actualAllProducts.getBody().toList().isEmpty());
        assertTrue(actualAllProducts.hasBody());
        assertEquals(HttpStatus.OK, actualAllProducts.getStatusCode());
        assertTrue(actualAllProducts.getHeaders().isEmpty());
        verify(productRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindProductById() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        when(this.productServiceImpl.findById((String) any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"name\":\"Name\",\"upc\":\"Upc\",\"price\":1,"
                                        + "\"inventoryThreshold\":1}"));
    }

    @Test
    void testFindProductByUpc() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        when(this.productServiceImpl.findByUpc((String) any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/upc/{upc}", "Upc");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"name\":\"Name\",\"upc\":\"Upc\",\"price\":1,"
                                        + "\"inventoryThreshold\":1}"));
    }

    @Test
    void testGetAllProducts() throws Exception {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        when(this.productServiceImpl.findById((String) any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{id}/inventory", "",
                "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"name\":\"Name\",\"upc\":\"Upc\",\"price\":1,"
                                        + "\"inventoryThreshold\":1}"));
    }

    @Test
    void testUpdateProduct() throws Exception {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");
        String content = (new ObjectMapper()).writeValueAsString(productDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/{id}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.productController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

