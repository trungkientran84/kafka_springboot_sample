package com.t.t.k.ims.model.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class ProductTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Product()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        Product product = new Product();

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(0);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertTrue(product.canEqual(product1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        Product actualProduct = new Product();
        actualProduct.setId("42");
        actualProduct.setInventoryThreshold(1);
        actualProduct.setName("Name");
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        actualProduct.setPrice(valueOfResult);
        actualProduct.setUpc("Upc");

        // Assert
        assertEquals("42", actualProduct.getId());
        assertEquals(1, actualProduct.getInventoryThreshold().intValue());
        assertEquals("Name", actualProduct.getName());
        BigDecimal expectedPrice = valueOfResult.ONE;
        assertSame(expectedPrice, actualProduct.getPrice());
        assertEquals("Upc", actualProduct.getUpc());
        assertEquals("Product(id=42, name=Name, upc=Upc, price=1, inventoryThreshold=1)", actualProduct.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals("Different type to Product"));
    }

    @Test
    void testEquals3() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        // Act and Assert
        assertTrue(product.equals(product));
        int expectedHashCodeResult = product.hashCode();
        assertEquals(expectedHashCodeResult, product.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertTrue(product.equals(product1));
        int expectedHashCodeResult = product.hashCode();
        assertEquals(expectedHashCodeResult, product1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("Name");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals6() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId(null);
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals7() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(0);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals8() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(null);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals9() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("42");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals10() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName(null);
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals11() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(0L));
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals12() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(null);
        product.setUpc("Upc");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals13() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc("42");

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }

    @Test
    void testEquals14() {
        // Arrange
        Product product = new Product();
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setId("42");
        product.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setInventoryThreshold(1);
        product.setName("Name");
        product.setPrice(BigDecimal.valueOf(1L));
        product.setUpc(null);

        Product product1 = new Product();
        product1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setId("42");
        product1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product1.setInventoryThreshold(1);
        product1.setName("Name");
        product1.setPrice(BigDecimal.valueOf(1L));
        product1.setUpc("Upc");

        // Act and Assert
        assertFalse(product.equals(product1));
    }
}

