package com.t.t.k.ims.model.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ItemTest {
    @Test
    void testConstructor() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        BigDecimal valueOfResult1 = BigDecimal.valueOf(1L);

        // Act
        Item actualItem = new Item("42", "Product Name", 1, valueOfResult, valueOfResult1);

        // Assert
        BigDecimal expectedAmount = valueOfResult1.ONE;
        BigDecimal amount = actualItem.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(1, actualItem.getQuantity().intValue());
        assertSame(amount, actualItem.getPrice());
        assertEquals("Product Name", actualItem.getProductName());
        assertEquals("42", actualItem.getProductId());
        assertEquals("1", amount.toString());
        assertEquals(0, amount.scale());
        assertEquals(1, amount.signum());
        assertSame(amount, valueOfResult);
        assertSame(amount, valueOfResult1);
    }

    @Test
    void testConstructor2() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);

        // Act
        Item actualItem = new Item("42", "Product Name", 1, valueOfResult);

        // Assert
        BigDecimal expectedAmount = valueOfResult.ONE;
        BigDecimal amount = actualItem.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(1, actualItem.getQuantity().intValue());
        assertSame(amount, actualItem.getPrice());
        assertEquals("Product Name", actualItem.getProductName());
        assertEquals("42", actualItem.getProductId());
        assertEquals("1", amount.toString());
    }

    @Test
    void testInCreaseQuantity() {
        // Arrange
        Item item = new Item("42", "Product Name", 1, BigDecimal.valueOf(1L));

        // Act
        item.increaseQuantity(1);

        // Assert
        assertEquals(2, item.getQuantity().intValue());
    }
}

