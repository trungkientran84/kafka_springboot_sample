package com.t.t.k.ims.controller.stores.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StoreDTOTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new StoreDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();

        StoreDTO storeDTO1 = new StoreDTO();
        storeDTO1.setName("Name");

        // Act and Assert
        assertTrue(storeDTO.canEqual(storeDTO1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        StoreDTO actualStoreDTO = new StoreDTO();
        actualStoreDTO.setName("Name");

        // Assert
        assertEquals("Name", actualStoreDTO.getName());
        assertEquals("StoreDTO(name=Name)", actualStoreDTO.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");

        // Act and Assert
        assertFalse(storeDTO.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");

        // Act and Assert
        assertFalse(storeDTO.equals("Different type to StoreDTO"));
    }

    @Test
    void testEquals3() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");

        // Act and Assert
        assertTrue(storeDTO.equals(storeDTO));
        int expectedHashCodeResult = storeDTO.hashCode();
        assertEquals(expectedHashCodeResult, storeDTO.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("Name");

        StoreDTO storeDTO1 = new StoreDTO();
        storeDTO1.setName("Name");

        // Act and Assert
        assertTrue(storeDTO.equals(storeDTO1));
        int expectedHashCodeResult = storeDTO.hashCode();
        assertEquals(expectedHashCodeResult, storeDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName(null);

        StoreDTO storeDTO1 = new StoreDTO();
        storeDTO1.setName("Name");

        // Act and Assert
        assertFalse(storeDTO.equals(storeDTO1));
    }

    @Test
    void testEquals6() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName("com.t.t.k.ims.controller.stores.dto.StoreDTO");

        StoreDTO storeDTO1 = new StoreDTO();
        storeDTO1.setName("Name");

        // Act and Assert
        assertFalse(storeDTO.equals(storeDTO1));
    }

    @Test
    void testEquals7() {
        // Arrange
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName(null);

        StoreDTO storeDTO1 = new StoreDTO();
        storeDTO1.setName(null);

        // Act and Assert
        assertTrue(storeDTO.equals(storeDTO1));
        int expectedHashCodeResult = storeDTO.hashCode();
        assertEquals(expectedHashCodeResult, storeDTO1.hashCode());
    }
}

