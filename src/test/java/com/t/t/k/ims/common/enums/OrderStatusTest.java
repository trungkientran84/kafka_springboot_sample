package com.t.t.k.ims.common.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OrderStatusTest {
    @Test
    void testValueOf() {
        // Arrange, Act and Assert
        assertEquals(OrderStatus.CANCELED, OrderStatus.valueOf("CANCELED"));
        assertEquals(OrderStatus.COMPLETED, OrderStatus.valueOf("COMPLETED"));
        assertEquals(OrderStatus.CONFIRMED, OrderStatus.valueOf("CONFIRMED"));
        assertEquals(OrderStatus.DELETED, OrderStatus.valueOf("DELETED"));
        assertEquals(OrderStatus.OPEN, OrderStatus.valueOf("OPEN"));
    }

    @Test
    void testValues() {
        // Arrange and Act
        OrderStatus[] actualValuesResult = OrderStatus.values();

        // Assert
        assertEquals(5, actualValuesResult.length);
        assertEquals(OrderStatus.OPEN, actualValuesResult[0]);
        assertEquals(OrderStatus.CONFIRMED, actualValuesResult[1]);
        assertEquals(OrderStatus.COMPLETED, actualValuesResult[2]);
        assertEquals(OrderStatus.CANCELED, actualValuesResult[3]);
        assertEquals(OrderStatus.DELETED, actualValuesResult[4]);
    }
}

