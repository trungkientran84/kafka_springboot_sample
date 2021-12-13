package com.t.t.k.ims.model.stores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class StoreTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Store()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        Store store = new Store();

        Store store1 = new Store();
        store1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setId("42");
        store1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setName("Name");

        // Act and Assert
        assertTrue(store.canEqual(store1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        Store actualStore = new Store();
        actualStore.setId("42");
        actualStore.setName("Name");

        // Assert
        assertEquals("42", actualStore.getId());
        assertEquals("Name", actualStore.getName());
        assertEquals("Store(id=42, name=Name)", actualStore.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");

        // Act and Assert
        assertFalse(store.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");

        // Act and Assert
        assertFalse(store.equals("Different type to Store"));
    }

    @Test
    void testEquals3() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");

        // Act and Assert
        assertTrue(store.equals(store));
        int expectedHashCodeResult = store.hashCode();
        assertEquals(expectedHashCodeResult, store.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");

        Store store1 = new Store();
        store1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setId("42");
        store1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setName("Name");

        // Act and Assert
        assertTrue(store.equals(store1));
        int expectedHashCodeResult = store.hashCode();
        assertEquals(expectedHashCodeResult, store1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(0, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");

        Store store1 = new Store();
        store1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setId("42");
        store1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setName("Name");

        // Act and Assert
        assertFalse(store.equals(store1));
    }

    @Test
    void testEquals6() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("Name");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");

        Store store1 = new Store();
        store1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setId("42");
        store1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setName("Name");

        // Act and Assert
        assertFalse(store.equals(store1));
    }

    @Test
    void testEquals7() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId(null);
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("Name");

        Store store1 = new Store();
        store1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setId("42");
        store1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setName("Name");

        // Act and Assert
        assertFalse(store.equals(store1));
    }

    @Test
    void testEquals8() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName("42");

        Store store1 = new Store();
        store1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setId("42");
        store1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setName("Name");

        // Act and Assert
        assertFalse(store.equals(store1));
    }

    @Test
    void testEquals9() {
        // Arrange
        Store store = new Store();
        store.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setId("42");
        store.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store.setName(null);

        Store store1 = new Store();
        store1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setId("42");
        store1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        store1.setName("Name");

        // Act and Assert
        assertFalse(store.equals(store1));
    }
}

