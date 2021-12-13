package com.t.t.k.ims.controller.products.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InventoryDTOTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new InventoryDTO("42", 3)).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO("42", 3);

        // Act and Assert
        assertTrue(inventoryDTO.canEqual(new InventoryDTO("42", 3)));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        InventoryDTO actualInventoryDTO = new InventoryDTO("42", 3);
        actualInventoryDTO.setCount(3);
        actualInventoryDTO.setStoreId("42");

        // Assert
        assertEquals(3, actualInventoryDTO.getCount().intValue());
        assertEquals("42", actualInventoryDTO.getStoreId());
        assertEquals("InventoryDTO(storeId=42, count=3)", actualInventoryDTO.toString());
    }

    @Test
    void testEquals() {
        // Arrange, Act and Assert
        assertFalse((new InventoryDTO("42", 3)).equals(null));
        assertFalse((new InventoryDTO("42", 3)).equals("Different type to InventoryDTO"));
    }

    @Test
    void testEquals2() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO("42", 3);

        // Act and Assert
        assertTrue(inventoryDTO.equals(inventoryDTO));
        int expectedHashCodeResult = inventoryDTO.hashCode();
        assertEquals(expectedHashCodeResult, inventoryDTO.hashCode());
    }

    @Test
    void testEquals3() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO("42", 3);
        InventoryDTO inventoryDTO1 = new InventoryDTO("42", 3);

        // Act and Assert
        assertTrue(inventoryDTO.equals(inventoryDTO1));
        int expectedHashCodeResult = inventoryDTO.hashCode();
        assertEquals(expectedHashCodeResult, inventoryDTO1.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO(null, 3);

        // Act and Assert
        assertFalse(inventoryDTO.equals(new InventoryDTO("42", 3)));
    }

    @Test
    void testEquals5() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO("Store Id", 3);

        // Act and Assert
        assertFalse(inventoryDTO.equals(new InventoryDTO("42", 3)));
    }

    @Test
    void testEquals6() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO("42", 0);

        // Act and Assert
        assertFalse(inventoryDTO.equals(new InventoryDTO("42", 3)));
    }

    @Test
    void testEquals7() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO("42", null);

        // Act and Assert
        assertFalse(inventoryDTO.equals(new InventoryDTO("42", 3)));
    }

    @Test
    void testEquals8() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO(null, 3);
        InventoryDTO inventoryDTO1 = new InventoryDTO(null, 3);

        // Act and Assert
        assertTrue(inventoryDTO.equals(inventoryDTO1));
        int expectedHashCodeResult = inventoryDTO.hashCode();
        assertEquals(expectedHashCodeResult, inventoryDTO1.hashCode());
    }

    @Test
    void testEquals9() {
        // Arrange
        InventoryDTO inventoryDTO = new InventoryDTO("42", null);
        InventoryDTO inventoryDTO1 = new InventoryDTO("42", null);

        // Act and Assert
        assertTrue(inventoryDTO.equals(inventoryDTO1));
        int expectedHashCodeResult = inventoryDTO.hashCode();
        assertEquals(expectedHashCodeResult, inventoryDTO1.hashCode());
    }
}

