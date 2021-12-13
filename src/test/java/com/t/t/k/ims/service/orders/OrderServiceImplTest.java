package com.t.t.k.ims.service.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.common.enums.OrderStatus;
import com.t.t.k.ims.common.enums.PaymentMethod;
import com.t.t.k.ims.controller.orders.dto.ItemDTO;
import com.t.t.k.ims.controller.orders.dto.OrderDTO;
import com.t.t.k.ims.controller.orders.dto.PaymentDTO;
import com.t.t.k.ims.model.orders.Item;
import com.t.t.k.ims.model.orders.Order;
import com.t.t.k.ims.model.orders.Payment;
import com.t.t.k.ims.model.products.Product;
import com.t.t.k.ims.repository.orders.OrderRepository;
import com.t.t.k.ims.repository.products.InventoryRepository;
import com.t.t.k.ims.repository.products.ProductRepository;
import com.t.t.k.ims.service.products.ProductServiceImpl;
import com.t.t.k.ims.validation.exception.ApiException;
import com.t.t.k.ims.validation.exception.ObjectNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@DirtiesContext
@ExtendWith(SpringExtension.class)
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
@TestPropertySource(properties = { "app.config.tax-percent=0.13" })
class OrderServiceImplTest {
    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    ProductServiceImpl productService;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Test
    void testConstructor() {

        // Arrange
        OrderRepository orderRepository = mock(OrderRepository.class);
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act
        new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster());

    }

    @Test
    void testAddOrder() {
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
        when(this.orderRepository.save((Order) any())).thenReturn(order);

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId("42");

        // Act
        Order actualAddOrderResult = this.orderServiceImpl.addOrder(orderDTO);

        // Assert
        assertSame(order, actualAddOrderResult);
        assertEquals("1", actualAddOrderResult.getTotalAmount().toString());
        verify(this.orderRepository).save((Order) any());
    }

    @Test
    void testDeleteOrder() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        doNothing().when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(ofResult);
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act
        (new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster())).deleteOrder("42");

        // Assert
        verify(orderRepository).deleteById((String) any());
        verify(orderRepository).findById((String) any());
    }

    @Test
    void testDeleteOrder2() throws Exception {
        // Arrange
        HashMap<String, Payment> stringPaymentMap = new HashMap<String, Payment>(1);
        stringPaymentMap.put("foo", new Payment(PaymentMethod.CASH, BigDecimal.valueOf(1L)));

        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(stringPaymentMap);
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        doNothing().when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(ofResult);
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act
        (new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster())).deleteOrder("42");

        // Assert
        verify(orderRepository).deleteById((String) any());
        verify(orderRepository).findById((String) any());
    }

    @Test
    void testDeleteOrder3() throws Exception {
        // Arrange
        HashMap<String, Item> stringItemMap = new HashMap<String, Item>(1);
        stringItemMap.put("foo", new Item("42", "Product Name", 1, BigDecimal.valueOf(1L)));

        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(stringItemMap);
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        doNothing().when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(ofResult);
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act
        (new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster())).deleteOrder("42");

        // Assert
        verify(orderRepository).deleteById((String) any());
        verify(orderRepository).findById((String) any());
    }

    @Test
    void testDeleteOrder4() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.COMPLETED);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        doNothing().when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(ofResult);
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act and Assert
        assertThrows(ApiException.class,
                () -> (new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster()))
                        .deleteOrder("42"));
        verify(orderRepository).findById((String) any());
    }

    @Test
    void testDeleteOrder5() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.CANCELED);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);
        OrderRepository orderRepository = mock(OrderRepository.class);
        doNothing().when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(ofResult);
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act and Assert
        assertThrows(ApiException.class,
                () -> (new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster()))
                        .deleteOrder("42"));
        verify(orderRepository).findById((String) any());
    }

    @Test
    void testDeleteOrder6() throws Exception {
        // Arrange
        OrderRepository orderRepository = mock(OrderRepository.class);
        doNothing().when(orderRepository).deleteById((String) any());
        when(orderRepository.findById((String) any())).thenReturn(Optional.<Order>empty());
        ProductRepository productRepository = mock(ProductRepository.class);
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class,
                () -> (new OrderServiceImpl(orderRepository, productService, new SimpleApplicationEventMulticaster()))
                        .deleteOrder("42"));
        verify(orderRepository).findById((String) any());
    }

    @Test
    void testFindById() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act
        Order actualFindByIdResult = this.orderServiceImpl.findById("42");

        // Assert
        assertSame(order, actualFindByIdResult);
        assertEquals("1", actualFindByIdResult.getTotalAmount().toString());
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testFindById2() throws Exception {
        // Arrange
        when(this.orderRepository.findById((String) any())).thenReturn(Optional.<Order>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.orderServiceImpl.findById("42"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testFindAllOrders() {
        // Arrange
        PageImpl<Order> pageImpl = new PageImpl<Order>(new ArrayList<Order>());
        when(this.orderRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<Order> actualFindAllOrdersResult = this.orderServiceImpl.findAllOrders(null);

        // Assert
        assertSame(pageImpl, actualFindAllOrdersResult);
        assertTrue(actualFindAllOrdersResult.toList().isEmpty());
        verify(this.orderRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testFindAllOrdersByProductUpc() {
        // Arrange
        OrderRepository orderRepository = mock(OrderRepository.class);
        PageImpl<Order> pageImpl = new PageImpl<Order>(new ArrayList<Order>());
        when(orderRepository.findAllByProductUpc((String) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(pageImpl);

        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.findByUpc((String) any())).thenReturn(Optional.<Product>of(product));
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ProductServiceImpl productService = new ProductServiceImpl(productRepository, inventoryRepository,
                new SimpleApplicationEventMulticaster());

        // Act
        Page<Order> actualFindAllOrdersByProductUpcResult = (new OrderServiceImpl(orderRepository, productService,
                new SimpleApplicationEventMulticaster())).findAllOrdersByProductUpc("Upc", null);

        // Assert
        assertSame(pageImpl, actualFindAllOrdersByProductUpcResult);
        assertTrue(actualFindAllOrdersByProductUpcResult.toList().isEmpty());
        verify(orderRepository).findAllByProductUpc((String) any(), (org.springframework.data.domain.Pageable) any());
        verify(productRepository).findByUpc((String) any());
    }

    @Test
    void testAddItem() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.CONFIRMED);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("Upc");

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.addItem("42", itemDTO));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testAddItem2() throws Exception {
        // Arrange
        when(this.orderRepository.findById((String) any())).thenReturn(Optional.<Order>empty());

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("Upc");

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.orderServiceImpl.addItem("42", itemDTO));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testRemoveItem() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.removeItem("42", "Pid"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testRemoveItem2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.CONFIRMED);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.removeItem("42", "Pid"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testRemoveItem3() throws Exception {
        // Arrange
        when(this.orderRepository.findById((String) any())).thenReturn(Optional.<Order>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.orderServiceImpl.removeItem("42", "Pid"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testConfirm() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.confirm("42"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testConfirm2() throws Exception {
        // Arrange
        HashMap<String, Item> stringItemMap = new HashMap<String, Item>(1);
        stringItemMap.put("It only allows to confirm an order if it contains at least one item",
                new Item("42", "Product Name", 1, BigDecimal.valueOf(1L)));

        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(stringItemMap);
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);

        Order order1 = new Order();
        order1.setDiscountAmount(BigDecimal.valueOf(1L));
        order1.setTaxPercent(BigDecimal.valueOf(1L));
        order1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order1.setPayments(new HashMap<String, Payment>(1));
        order1.setCashierId("42");
        order1.setItems(new HashMap<String, Item>(1));
        order1.setPromotions(new ArrayList<String>());
        order1.setStatus(OrderStatus.OPEN);
        order1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order1.setId("42");
        order1.setTotalAmount(BigDecimal.valueOf(1L));
        order1.setSubAmount(BigDecimal.valueOf(1L));
        order1.setStoreId("42");
        order1.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderRepository.save((Order) any())).thenReturn(order1);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act
        Order actualConfirmResult = this.orderServiceImpl.confirm("42");

        // Assert
        assertSame(order1, actualConfirmResult);
        assertEquals("1", actualConfirmResult.getTotalAmount().toString());
        verify(this.orderRepository).findById((String) any());
        verify(this.orderRepository).save((Order) any());
    }

    @Test
    void testAddPayment() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);
        paymentDTO.setAmount(BigDecimal.valueOf(1L));

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.addPayment("42", paymentDTO));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testAddPayment2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.CONFIRMED);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);

        Order order1 = new Order();
        order1.setDiscountAmount(BigDecimal.valueOf(1L));
        order1.setTaxPercent(BigDecimal.valueOf(1L));
        order1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order1.setPayments(new HashMap<String, Payment>(1));
        order1.setCashierId("42");
        order1.setItems(new HashMap<String, Item>(1));
        order1.setPromotions(new ArrayList<String>());
        order1.setStatus(OrderStatus.OPEN);
        order1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order1.setId("42");
        order1.setTotalAmount(BigDecimal.valueOf(1L));
        order1.setSubAmount(BigDecimal.valueOf(1L));
        order1.setStoreId("42");
        order1.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderRepository.save((Order) any())).thenReturn(order1);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);
        paymentDTO.setAmount(BigDecimal.valueOf(1L));

        // Act
        Order actualAddPaymentResult = this.orderServiceImpl.addPayment("42", paymentDTO);

        // Assert
        assertSame(order1, actualAddPaymentResult);
        assertEquals("1", actualAddPaymentResult.getTotalAmount().toString());
        verify(this.orderRepository).findById((String) any());
        verify(this.orderRepository).save((Order) any());
    }

    @Test
    void testRemovePayment() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.removePayment("42", "Pid"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testRemovePayment2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.CONFIRMED);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.removePayment("42", "Pid"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testRemovePayment3() throws Exception {
        // Arrange
        when(this.orderRepository.findById((String) any())).thenReturn(Optional.<Order>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.orderServiceImpl.removePayment("42", "Pid"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testClose() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.close("42"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testClose2() throws Exception {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.CONFIRMED);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ApiException.class, () -> this.orderServiceImpl.close("42"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testClose3() throws Exception {
        // Arrange
        when(this.orderRepository.findById((String) any())).thenReturn(Optional.<Order>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.orderServiceImpl.close("42"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testCancel() throws Exception {

        // Arrange
        BigDecimal discountAmount = BigDecimal.valueOf(1L);

        Order order = new Order();
        order.setDiscountAmount(discountAmount);
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
        when(this.orderRepository.save((Order) any())).thenReturn(order);
        BigDecimal discountAmount1 = BigDecimal.valueOf(1L);

        Order order1 = new Order();
        order1.setDiscountAmount(discountAmount1);
        order1.setTaxPercent(BigDecimal.valueOf(1L));
        order1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order1.setPayments(new HashMap<String, Payment>(1));
        order1.setCashierId("42");
        order1.setItems(new HashMap<String, Item>(1));
        order1.setPromotions(new ArrayList<String>());
        order1.setStatus(OrderStatus.OPEN);
        order1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order1.setId("42");
        order1.setTotalAmount(BigDecimal.valueOf(1L));
        order1.setSubAmount(BigDecimal.valueOf(1L));
        order1.setStoreId("42");
        order1.setTaxAmount(BigDecimal.valueOf(1L));
        when(this.orderRepository.findById((String) any())).thenReturn(Optional.<Order>of(order1));

        // Act
        this.orderServiceImpl.cancel("42");
    }

    @Test
    void testGetAllProductsInOrder() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertTrue(this.orderServiceImpl.getAllProductsInOrder("42").isEmpty());
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testGetAllProductsInOrder2() throws Exception {
        // Arrange
        when(this.orderRepository.findById((String) any())).thenReturn(Optional.<Order>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.orderServiceImpl.getAllProductsInOrder("42"));
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testGetPaymentsInOrder() throws Exception {
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
        Optional<Order> ofResult = Optional.<Order>of(order);
        when(this.orderRepository.findById((String) any())).thenReturn(ofResult);

        // Act and Assert
        assertTrue(this.orderServiceImpl.getPaymentsInOrder("42").isEmpty());
        verify(this.orderRepository).findById((String) any());
    }

    @Test
    void testGetPaymentsInOrder2() throws Exception {
        // Arrange
        when(this.orderRepository.findById((String) any())).thenReturn(Optional.<Order>empty());

        // Act and Assert
        assertThrows(ObjectNotFoundException.class, () -> this.orderServiceImpl.getPaymentsInOrder("42"));
        verify(this.orderRepository).findById((String) any());
    }
}

