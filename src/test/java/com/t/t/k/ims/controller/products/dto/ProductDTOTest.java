package com.t.t.k.ims.controller.products.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProductDTOTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ProductDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(0);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertTrue(productDTO.canEqual(productDTO1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        ProductDTO actualProductDTO = new ProductDTO();
        actualProductDTO.setInventoryThreshold(1);
        actualProductDTO.setName("Name");
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        actualProductDTO.setPrice(valueOfResult);
        actualProductDTO.setUpc("Upc");

        // Assert
        assertEquals(1, actualProductDTO.getInventoryThreshold().intValue());
        assertEquals("Name", actualProductDTO.getName());
        BigDecimal expectedPrice = valueOfResult.ONE;
        assertSame(expectedPrice, actualProductDTO.getPrice());
        assertEquals("Upc", actualProductDTO.getUpc());
        assertEquals("ProductDTO(name=Name, upc=Upc, price=1, inventoryThreshold=1)", actualProductDTO.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals("Different type to ProductDTO"));
    }

    @Test
    void testEquals3() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        // Act and Assert
        assertTrue(productDTO.equals(productDTO));
        int expectedHashCodeResult = productDTO.hashCode();
        assertEquals(expectedHashCodeResult, productDTO.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertTrue(productDTO.equals(productDTO1));
        int expectedHashCodeResult = productDTO.hashCode();
        assertEquals(expectedHashCodeResult, productDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(0);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(productDTO1));
    }

    @Test
    void testEquals6() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(null);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(productDTO1));
    }

    @Test
    void testEquals7() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Upc");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(productDTO1));
    }

    @Test
    void testEquals8() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName(null);
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(productDTO1));
    }

    @Test
    void testEquals9() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(0L));
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(productDTO1));
    }

    @Test
    void testEquals10() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(null);
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(productDTO1));
    }

    @Test
    void testEquals11() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Name");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(productDTO1));
    }

    @Test
    void testEquals12() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc(null);

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(productDTO.equals(productDTO1));
    }

    @Test
    void testEquals13() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(null);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(null);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertTrue(productDTO.equals(productDTO1));
        int expectedHashCodeResult = productDTO.hashCode();
        assertEquals(expectedHashCodeResult, productDTO1.hashCode());
    }

    @Test
    void testEquals14() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName(null);
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName(null);
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertTrue(productDTO.equals(productDTO1));
        int expectedHashCodeResult = productDTO.hashCode();
        assertEquals(expectedHashCodeResult, productDTO1.hashCode());
    }

    @Test
    void testEquals15() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(null);
        productDTO.setUpc("Upc");

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(null);
        productDTO1.setUpc("Upc");

        // Act and Assert
        assertTrue(productDTO.equals(productDTO1));
        int expectedHashCodeResult = productDTO.hashCode();
        assertEquals(expectedHashCodeResult, productDTO1.hashCode());
    }

    @Test
    void testEquals16() {
        // Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setInventoryThreshold(1);
        productDTO.setName("Name");
        productDTO.setPrice(BigDecimal.valueOf(1L));
        productDTO.setUpc(null);

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setInventoryThreshold(1);
        productDTO1.setName("Name");
        productDTO1.setPrice(BigDecimal.valueOf(1L));
        productDTO1.setUpc(null);

        // Act and Assert
        assertTrue(productDTO.equals(productDTO1));
        int expectedHashCodeResult = productDTO.hashCode();
        assertEquals(expectedHashCodeResult, productDTO1.hashCode());
    }
}

