package com.t.t.k.ims.controller.orders.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ItemDTOTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ItemDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setQuantity(0);
        itemDTO1.setUpc("Upc");

        // Act and Assert
        assertTrue(itemDTO.canEqual(itemDTO1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        ItemDTO actualItemDTO = new ItemDTO();
        actualItemDTO.setQuantity(1);
        actualItemDTO.setUpc("Upc");

        // Assert
        assertEquals(1, actualItemDTO.getQuantity().intValue());
        assertEquals("Upc", actualItemDTO.getUpc());
        assertEquals("ItemDTO(upc=Upc, quantity=1)", actualItemDTO.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("Upc");

        // Act and Assert
        assertFalse(itemDTO.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("Upc");

        // Act and Assert
        assertFalse(itemDTO.equals("Different type to ItemDTO"));
    }

    @Test
    void testEquals3() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("Upc");

        // Act and Assert
        assertTrue(itemDTO.equals(itemDTO));
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("Upc");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setQuantity(1);
        itemDTO1.setUpc("Upc");

        // Act and Assert
        assertTrue(itemDTO.equals(itemDTO1));
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(0);
        itemDTO.setUpc("Upc");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setQuantity(1);
        itemDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(itemDTO.equals(itemDTO1));
    }

    @Test
    void testEquals6() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(null);
        itemDTO.setUpc("Upc");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setQuantity(1);
        itemDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(itemDTO.equals(itemDTO1));
    }

    @Test
    void testEquals7() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc(null);

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setQuantity(1);
        itemDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(itemDTO.equals(itemDTO1));
    }

    @Test
    void testEquals8() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc("com.t.t.k.ims.controller.orders.dto.ItemDTO");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setQuantity(1);
        itemDTO1.setUpc("Upc");

        // Act and Assert
        assertFalse(itemDTO.equals(itemDTO1));
    }

    @Test
    void testEquals9() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(null);
        itemDTO.setUpc("Upc");

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setQuantity(null);
        itemDTO1.setUpc("Upc");

        // Act and Assert
        assertTrue(itemDTO.equals(itemDTO1));
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO1.hashCode());
    }

    @Test
    void testEquals10() {
        // Arrange
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(1);
        itemDTO.setUpc(null);

        ItemDTO itemDTO1 = new ItemDTO();
        itemDTO1.setQuantity(1);
        itemDTO1.setUpc(null);

        // Act and Assert
        assertTrue(itemDTO.equals(itemDTO1));
        int expectedHashCodeResult = itemDTO.hashCode();
        assertEquals(expectedHashCodeResult, itemDTO1.hashCode());
    }
}

