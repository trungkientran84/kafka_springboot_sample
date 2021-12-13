package com.t.t.k.ims.controller.orders.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OrderDTOTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new OrderDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();

        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setCashierId("42");
        orderDTO1.setStoreId("42");

        // Act and Assert
        assertTrue(orderDTO.canEqual(orderDTO1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        OrderDTO actualOrderDTO = new OrderDTO();
        actualOrderDTO.setCashierId("42");
        actualOrderDTO.setStoreId("42");

        // Assert
        assertEquals("42", actualOrderDTO.getCashierId());
        assertEquals("42", actualOrderDTO.getStoreId());
        assertEquals("OrderDTO(cashierId=42, storeId=42)", actualOrderDTO.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId("42");

        // Act and Assert
        assertFalse(orderDTO.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId("42");

        // Act and Assert
        assertFalse(orderDTO.equals("Different type to OrderDTO"));
    }

    @Test
    void testEquals3() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId("42");

        // Act and Assert
        assertTrue(orderDTO.equals(orderDTO));
        int expectedHashCodeResult = orderDTO.hashCode();
        assertEquals(expectedHashCodeResult, orderDTO.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId("42");

        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setCashierId("42");
        orderDTO1.setStoreId("42");

        // Act and Assert
        assertTrue(orderDTO.equals(orderDTO1));
        int expectedHashCodeResult = orderDTO.hashCode();
        assertEquals(expectedHashCodeResult, orderDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId(null);
        orderDTO.setStoreId("42");

        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setCashierId("42");
        orderDTO1.setStoreId("42");

        // Act and Assert
        assertFalse(orderDTO.equals(orderDTO1));
    }

    @Test
    void testEquals6() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("Cashier Id");
        orderDTO.setStoreId("42");

        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setCashierId("42");
        orderDTO1.setStoreId("42");

        // Act and Assert
        assertFalse(orderDTO.equals(orderDTO1));
    }

    @Test
    void testEquals7() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId(null);

        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setCashierId("42");
        orderDTO1.setStoreId("42");

        // Act and Assert
        assertFalse(orderDTO.equals(orderDTO1));
    }

    @Test
    void testEquals8() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId("Store Id");

        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setCashierId("42");
        orderDTO1.setStoreId("42");

        // Act and Assert
        assertFalse(orderDTO.equals(orderDTO1));
    }

    @Test
    void testEquals9() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId(null);
        orderDTO.setStoreId("42");

        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setCashierId(null);
        orderDTO1.setStoreId("42");

        // Act and Assert
        assertTrue(orderDTO.equals(orderDTO1));
        int expectedHashCodeResult = orderDTO.hashCode();
        assertEquals(expectedHashCodeResult, orderDTO1.hashCode());
    }

    @Test
    void testEquals10() {
        // Arrange
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCashierId("42");
        orderDTO.setStoreId(null);

        OrderDTO orderDTO1 = new OrderDTO();
        orderDTO1.setCashierId("42");
        orderDTO1.setStoreId(null);

        // Act and Assert
        assertTrue(orderDTO.equals(orderDTO1));
        int expectedHashCodeResult = orderDTO.hashCode();
        assertEquals(expectedHashCodeResult, orderDTO1.hashCode());
    }
}

