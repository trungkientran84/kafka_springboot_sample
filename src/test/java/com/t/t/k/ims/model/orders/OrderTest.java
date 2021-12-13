package com.t.t.k.ims.model.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.t.t.k.ims.common.enums.OrderStatus;
import com.t.t.k.ims.common.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Order()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        Order order = new Order();

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

        // Act and Assert
        assertTrue(order.canEqual(order1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        Order actualOrder = new Order();
        actualOrder.setCashierId("42");
        actualOrder.setDiscountAmount(BigDecimal.valueOf(1L));
        actualOrder.setId("42");
        HashMap<String, Item> stringItemMap = new HashMap<String, Item>(1);
        actualOrder.setItems(stringItemMap);
        HashMap<String, Payment> stringPaymentMap = new HashMap<String, Payment>(1);
        actualOrder.setPayments(stringPaymentMap);
        ArrayList<String> stringList = new ArrayList<String>();
        actualOrder.setPromotions(stringList);
        actualOrder.setStatus(OrderStatus.OPEN);
        actualOrder.setStoreId("42");
        actualOrder.setSubAmount(BigDecimal.valueOf(1L));
        actualOrder.setTaxAmount(BigDecimal.valueOf(1L));
        actualOrder.setTaxPercent(BigDecimal.valueOf(1L));
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        actualOrder.setTotalAmount(valueOfResult);

        // Assert
        assertEquals("42", actualOrder.getCashierId());
        BigDecimal totalAmount = actualOrder.getTotalAmount();
        assertSame(totalAmount, actualOrder.getDiscountAmount());
        assertEquals("42", actualOrder.getId());
        assertSame(stringItemMap, actualOrder.getItems());
        assertSame(stringPaymentMap, actualOrder.getPayments());
        assertSame(stringList, actualOrder.getPromotions());
        assertEquals(OrderStatus.OPEN, actualOrder.getStatus());
        assertEquals("42", actualOrder.getStoreId());
        assertSame(totalAmount, actualOrder.getSubAmount());
        assertSame(totalAmount, actualOrder.getTaxAmount());
        assertSame(totalAmount, actualOrder.getTaxPercent());
        assertSame(valueOfResult.ONE, totalAmount);
        assertEquals(
                "Order(id=42, storeId=42, cashierId=42, items={}, payments={}, promotions=[], subAmount=1, discountAmount=1,"
                        + " taxPercent=1, taxAmount=1, totalAmount=1, status=OPEN)",
                actualOrder.toString());
    }

    @Test
    void testConstructor2() {
        // Arrange and Act
        Order actualOrder = new Order();

        // Assert
        assertEquals("Order(id=null, storeId=null, cashierId=null, items={}, payments={}, promotions=[], subAmount=0,"
                + " discountAmount=0, taxPercent=0, taxAmount=0, totalAmount=0, status=OPEN)", actualOrder.toString());
        BigDecimal totalAmount = actualOrder.getTotalAmount();
        BigDecimal taxPercent = actualOrder.getTaxPercent();
        assertEquals(totalAmount, taxPercent);
        assertEquals(OrderStatus.OPEN, actualOrder.getStatus());
        assertTrue(actualOrder.getItems().isEmpty());
        BigDecimal subAmount = actualOrder.getSubAmount();
        assertEquals(taxPercent, subAmount);
        BigDecimal taxAmount = actualOrder.getTaxAmount();
        assertEquals(subAmount, taxAmount);
        BigDecimal discountAmount = actualOrder.getDiscountAmount();
        assertEquals(subAmount, discountAmount);
        assertTrue(actualOrder.getPayments().isEmpty());
        assertTrue(actualOrder.getPromotions().isEmpty());
        assertEquals("0", taxPercent.toString());
        assertEquals("0", totalAmount.toString());
        assertEquals("0", taxAmount.toString());
        assertEquals("0", subAmount.toString());
        assertEquals("0", discountAmount.toString());
    }

    @Test
    void testEquals() {
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

        // Act and Assert
        assertFalse(order.equals(null));
    }

    @Test
    void testEquals2() {
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

        // Act and Assert
        assertFalse(order.equals("Different type to Order"));
    }

    @Test
    void testEquals3() {
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

        // Act and Assert
        assertTrue(order.equals(order));
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order.hashCode());
    }

    @Test
    void testEquals4() {
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

        // Act and Assert
        assertTrue(order.equals(order1));
        int expectedHashCodeResult = order.hashCode();
        assertEquals(expectedHashCodeResult, order1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(0L));
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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals6() {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(null);
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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals7() {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(0L));
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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals8() {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(null);
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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals9() {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(0, 1, 1, 1, 1));
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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals10() {
        // Arrange
        HashMap<String, Payment> stringPaymentMap = new HashMap<String, Payment>(1);
        stringPaymentMap.put("42", new Payment(PaymentMethod.CASH, BigDecimal.valueOf(1L)));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals11() {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId(null);
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals12() {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("Cashier Id");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals13() {
        // Arrange
        HashMap<String, Item> stringItemMap = new HashMap<String, Item>(1);
        stringItemMap.put("42", new Item("42", "Product Name", 1, BigDecimal.valueOf(1L)));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals14() {
        // Arrange
        ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("42");

        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(stringList);
        order.setStatus(OrderStatus.OPEN);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals15() {
        // Arrange
        Order order = new Order();
        order.setDiscountAmount(BigDecimal.valueOf(1L));
        order.setTaxPercent(BigDecimal.valueOf(1L));
        order.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setPayments(new HashMap<String, Payment>(1));
        order.setCashierId("42");
        order.setItems(new HashMap<String, Item>(1));
        order.setPromotions(new ArrayList<String>());
        order.setStatus(null);
        order.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setId("42");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals16() {
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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals17() {
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
        order.setId(null);
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals18() {
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
        order.setId("Id");
        order.setTotalAmount(BigDecimal.valueOf(1L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals19() {
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
        order.setTotalAmount(BigDecimal.valueOf(0L));
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals20() {
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
        order.setTotalAmount(null);
        order.setSubAmount(BigDecimal.valueOf(1L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals21() {
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
        order.setSubAmount(BigDecimal.valueOf(0L));
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals22() {
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
        order.setSubAmount(null);
        order.setStoreId("42");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals23() {
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
        order.setStoreId(null);
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals24() {
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
        order.setStoreId("Store Id");
        order.setTaxAmount(BigDecimal.valueOf(1L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals25() {
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
        order.setTaxAmount(BigDecimal.valueOf(0L));

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }

    @Test
    void testEquals26() {
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
        order.setTaxAmount(null);

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

        // Act and Assert
        assertFalse(order.equals(order1));
    }
}

