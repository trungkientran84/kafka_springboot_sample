package com.t.t.k.ims.controller.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t.t.k.ims.common.enums.OrderStatus;
import com.t.t.k.ims.common.enums.PaymentMethod;
import com.t.t.k.ims.controller.orders.dto.ItemDTO;
import com.t.t.k.ims.controller.orders.dto.OrderDTO;
import com.t.t.k.ims.controller.orders.dto.PaymentDTO;
import com.t.t.k.ims.model.orders.Item;
import com.t.t.k.ims.model.orders.Order;
import com.t.t.k.ims.model.orders.Payment;
import com.t.t.k.ims.repository.orders.OrderRepository;
import com.t.t.k.ims.repository.products.InventoryRepository;
import com.t.t.k.ims.repository.products.ProductRepository;
import com.t.t.k.ims.service.orders.OrderService;
import com.t.t.k.ims.service.orders.OrderServiceImpl;
import com.t.t.k.ims.service.products.ProductService;
import com.t.t.k.ims.service.products.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.event.ApplicationEventMulticaster;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderServiceImpl orderServiceImpl;

    @Test
    void testAddPaymenttToOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.addPayment((String) any(), (PaymentDTO) any())).thenReturn(order);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);
        String content = (new ObjectMapper()).writeValueAsString(paymentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/payments", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testAddPaymenttToOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.addPayment((String) any(), (PaymentDTO) any())).thenReturn(order);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        String content = (new ObjectMapper()).writeValueAsString(paymentDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/payments", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testAddProductToOrder() throws Exception {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("Upc");
        String content = (new ObjectMapper()).writeValueAsString(itemDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/items", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testAddProductToOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.addItem((String) any(), (ItemDTO) any())).thenReturn(order);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("");
        String content = (new ObjectMapper()).writeValueAsString(itemDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/items", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testAddProductToOrder3() throws Exception {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("Upc");
        String content = (new ObjectMapper()).writeValueAsString(itemDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/items", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    void testAddProductToOrder4() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.addItem((String) any(), (ItemDTO) any())).thenReturn(order);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("");
        String content = (new ObjectMapper()).writeValueAsString(itemDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/items", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testCancelOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.cancel((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/cancel", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testCancelOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.cancel((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/cancel", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testCloseOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.close((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/close", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testCloseOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.close((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/close", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testConfirmOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.confirm((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/confirm", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testConfirmOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.confirm((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders/{id}/confirm", "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testConstructor() {
        // Arrange
        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(mock(OrderRepository.class), mock(ProductService.class), mock(ApplicationEventMulticaster.class));

        // Act and Assert
        OrderService orderService = (new OrderController(orderServiceImpl)).orderService;
        assertTrue(orderService instanceof OrderServiceImpl);
        assertSame(orderService, orderServiceImpl);
    }

    @Test
    void testConstructor2() {
        // Arrange
        OrderRepository orderRepository = mock(OrderRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        OrderServiceImpl orderServiceImpl = new OrderServiceImpl(orderRepository, productService,
                new SimpleApplicationEventMulticaster());

        // Act and Assert
        OrderService orderService = (new OrderController(orderServiceImpl)).orderService;
        assertTrue(orderService instanceof OrderServiceImpl);
        assertSame(orderService, orderServiceImpl);
    }

    @Test
    void testAddOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save((Order) any())).thenReturn(order);
        OrderController orderController = new OrderController(
                new OrderServiceImpl(orderRepository, mock(ProductService.class), mock(ApplicationEventMulticaster.class)));

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId("42");

        // Act
        ResponseEntity<Order> actualAddOrderResult = orderController.addOrder(orderDTO);

        // Assert
        assertEquals(
                "<201 CREATED Created,Order(id=42, storeId=42, cashierId=42, items={}, payments={}, promotions=[],"
                        + " subAmount=1, discountAmount=1, taxPercent=1, taxAmount=1, totalAmount=1, status=OPEN),[]>",
                actualAddOrderResult.toString());
        assertTrue(actualAddOrderResult.getHeaders().isEmpty());
        assertTrue(actualAddOrderResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualAddOrderResult.getStatusCode());
        assertEquals("1", actualAddOrderResult.getBody().getTaxAmount().toString());
        verify(orderRepository).save((Order) any());
    }

    @Test
    void testAddOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.save((Order) any())).thenReturn(order);
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        OrderController orderController = new OrderController(
                new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster()));

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId("42");

        // Act
        ResponseEntity<Order> actualAddOrderResult = orderController.addOrder(orderDTO);

        // Assert
        assertEquals(
                "<201 CREATED Created,Order(id=42, storeId=42, cashierId=42, items={}, payments={}, promotions=[],"
                        + " subAmount=1, discountAmount=1, taxPercent=1, taxAmount=1, totalAmount=1, status=OPEN),[]>",
                actualAddOrderResult.toString());
        assertTrue(actualAddOrderResult.getHeaders().isEmpty());
        assertTrue(actualAddOrderResult.hasBody());
        assertEquals(HttpStatus.CREATED, actualAddOrderResult.getStatusCode());
        assertEquals("1", actualAddOrderResult.getBody().getTaxAmount().toString());
        verify(orderRepository).save((Order) any());
    }

    @Test
    void testDeleteOrder() throws Exception {
        // Arrange
        doNothing().when(this.orderServiceImpl).deleteOrder((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/orders/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteOrder2() throws Exception {
        // Arrange
        doNothing().when(this.orderServiceImpl).deleteOrder((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/orders/{id}", "42");
        deleteResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteOrder3() throws Exception {
        // Arrange
        doNothing().when(this.orderServiceImpl).deleteOrder((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/orders/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDeleteOrder4() throws Exception {
        // Arrange
        doNothing().when(this.orderServiceImpl).deleteOrder((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/orders/{id}", "42");
        deleteResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testFindAllOrderByProductUpc() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.findById((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/product/{upc}", "", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testFindOrderById2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.findById((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testGetAllOrders() throws Exception {
        // Arrange
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Order>(new ArrayList<Order>()));

        // Act
        ResponseEntity<Page<Order>> actualAllOrders = (new OrderController(
                new OrderServiceImpl(orderRepository, mock(ProductService.class), mock(ApplicationEventMulticaster.class))))
                .getAllOrders(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualAllOrders.toString());
        assertTrue(actualAllOrders.getBody().toList().isEmpty());
        assertTrue(actualAllOrders.hasBody());
        assertEquals(HttpStatus.OK, actualAllOrders.getStatusCode());
        assertTrue(actualAllOrders.getHeaders().isEmpty());
        verify(orderRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAllOrders2() throws Exception {
        // Arrange
        OrderRepository orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Order>(new ArrayList<Order>()));
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act
        ResponseEntity<Page<Order>> actualAllOrders = (new OrderController(
                new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster())))
                .getAllOrders(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>", actualAllOrders.toString());
        assertTrue(actualAllOrders.getBody().toList().isEmpty());
        assertTrue(actualAllOrders.hasBody());
        assertEquals(HttpStatus.OK, actualAllOrders.getStatusCode());
        assertTrue(actualAllOrders.getHeaders().isEmpty());
        verify(orderRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetPaymentsInOrder() throws Exception {
        // Arrange
        when(this.orderServiceImpl.getPaymentsInOrder((String) any())).thenReturn(new ArrayList<Payment>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}/payments", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPaymentsInOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.findById((String) any())).thenReturn(order);
        when(this.orderServiceImpl.getPaymentsInOrder((String) any())).thenReturn(new ArrayList<Payment>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}/payments", "", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testGetPaymentsInOrder3() throws Exception {
        // Arrange
        when(this.orderServiceImpl.getPaymentsInOrder((String) any())).thenReturn(new ArrayList<Payment>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}/payments", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPaymentsInOrder4() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.findById((String) any())).thenReturn(order);
        when(this.orderServiceImpl.getPaymentsInOrder((String) any())).thenReturn(new ArrayList<Payment>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}/payments", "", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testFindOrderById() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.findById((String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testGetPurchasedItemsInOrder() throws Exception {
        // Arrange
        when(this.orderServiceImpl.getAllProductsInOrder((String) any())).thenReturn(new ArrayList<Item>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}/items", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPurchasedItemsInOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.findById((String) any())).thenReturn(order);
        when(this.orderServiceImpl.getAllProductsInOrder((String) any())).thenReturn(new ArrayList<Item>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}/items", "", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testGetPurchasedItemsInOrder3() throws Exception {
        // Arrange
        when(this.orderServiceImpl.getAllProductsInOrder((String) any())).thenReturn(new ArrayList<Item>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}/items", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetPurchasedItemsInOrder4() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.findById((String) any())).thenReturn(order);
        when(this.orderServiceImpl.getAllProductsInOrder((String) any())).thenReturn(new ArrayList<Item>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}/items", "", "Uri Vars");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testRemovePaymentFromOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.removePayment((String) any(), (String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/orders/{id}/payments/{pid}", "42",
                "Pid");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testRemovePaymentFromOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.removePayment((String) any(), (String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/orders/{id}/payments/{pid}", "42",
                "Pid");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testRemoveProductFromOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setStatus(OrderStatus.OPEN);
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.removeItem((String) any(), (String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/orders/{id}/items/{pid}", "42",
                "Pid");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }

    @Test
    void testRemoveProductFromOrder2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderServiceImpl.removeItem((String) any(), (String) any())).thenReturn(order);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/orders/{id}/items/{pid}", "42",
                "Pid");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"createdAt\":[1,1,1,1,1],\"updatedAt\":[1,1,1,1,1],\"id\":\"42\",\"storeId\":\"42\",\"cashierId\":\"42\",\"items\":{"
                                        + "},\"payments\":{},\"promotions\":[],\"subAmount\":1,\"discountAmount\":1,\"taxPercent\":1,\"taxAmount\":1,\"totalAmount"
                                        + "\":1,\"status\":\"OPEN\"}"));
    }
}

