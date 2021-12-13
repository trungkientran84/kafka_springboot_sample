package com.t.t.k.ims.model.cashiers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class CashierTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new Cashier()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        Cashier cashier = new Cashier();

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertTrue(cashier.canEqual(cashier1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        Cashier actualCashier = new Cashier();
        actualCashier.setId("42");
        actualCashier.setName("Name");
        actualCashier.setStoreId("42");

        // Assert
        assertEquals("42", actualCashier.getId());
        assertEquals("Name", actualCashier.getName());
        assertEquals("42", actualCashier.getStoreId());
        assertEquals("Cashier(id=42, name=Name, storeId=42)", actualCashier.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals("Different type to Cashier"));
    }

    @Test
    void testEquals3() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");

        // Act and Assert
        assertTrue(cashier.equals(cashier));
        int expectedHashCodeResult = cashier.hashCode();
        assertEquals(expectedHashCodeResult, cashier.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertTrue(cashier.equals(cashier1));
        int expectedHashCodeResult = cashier.hashCode();
        assertEquals(expectedHashCodeResult, cashier1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(0, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals(cashier1));
    }

    @Test
    void testEquals6() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("Name");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals(cashier1));
    }

    @Test
    void testEquals7() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId(null);
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("42");

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals(cashier1));
    }

    @Test
    void testEquals8() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("42");
        cashier.setStoreId("42");

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals(cashier1));
    }

    @Test
    void testEquals9() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName(null);
        cashier.setStoreId("42");

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals(cashier1));
    }

    @Test
    void testEquals10() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId("Name");

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals(cashier1));
    }

    @Test
    void testEquals11() {
        // Arrange
        Cashier cashier = new Cashier();
        cashier.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setId("42");
        cashier.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier.setName("Name");
        cashier.setStoreId(null);

        Cashier cashier1 = new Cashier();
        cashier1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setId("42");
        cashier1.setUpdatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        cashier1.setName("Name");
        cashier1.setStoreId("42");

        // Act and Assert
        assertFalse(cashier.equals(cashier1));
    }
}

