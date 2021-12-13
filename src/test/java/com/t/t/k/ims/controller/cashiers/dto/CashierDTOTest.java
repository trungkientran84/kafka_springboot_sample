package com.t.t.k.ims.controller.cashiers.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CashierDTOTest {
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new CashierDTO()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();

        CashierDTO cashierDTO1 = new CashierDTO();
        cashierDTO1.setName("Name");
        cashierDTO1.setStoreId("42");

        // Act and Assert
        assertTrue(cashierDTO.canEqual(cashierDTO1));
    }

    @Test
    void testConstructor() {
        // Arrange and Act
        CashierDTO actualCashierDTO = new CashierDTO();
        actualCashierDTO.setName("Name");
        actualCashierDTO.setStoreId("42");

        // Assert
        assertEquals("Name", actualCashierDTO.getName());
        assertEquals("42", actualCashierDTO.getStoreId());
        assertEquals("CashierDTO(name=Name, storeId=42)", actualCashierDTO.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        // Act and Assert
        assertFalse(cashierDTO.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        // Act and Assert
        assertFalse(cashierDTO.equals("Different type to CashierDTO"));
    }

    @Test
    void testEquals3() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        // Act and Assert
        assertTrue(cashierDTO.equals(cashierDTO));
        int expectedHashCodeResult = cashierDTO.hashCode();
        assertEquals(expectedHashCodeResult, cashierDTO.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("42");

        CashierDTO cashierDTO1 = new CashierDTO();
        cashierDTO1.setName("Name");
        cashierDTO1.setStoreId("42");

        // Act and Assert
        assertTrue(cashierDTO.equals(cashierDTO1));
        int expectedHashCodeResult = cashierDTO.hashCode();
        assertEquals(expectedHashCodeResult, cashierDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("42");
        cashierDTO.setStoreId("42");

        CashierDTO cashierDTO1 = new CashierDTO();
        cashierDTO1.setName("Name");
        cashierDTO1.setStoreId("42");

        // Act and Assert
        assertFalse(cashierDTO.equals(cashierDTO1));
    }

    @Test
    void testEquals6() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName(null);
        cashierDTO.setStoreId("42");

        CashierDTO cashierDTO1 = new CashierDTO();
        cashierDTO1.setName("Name");
        cashierDTO1.setStoreId("42");

        // Act and Assert
        assertFalse(cashierDTO.equals(cashierDTO1));
    }

    @Test
    void testEquals7() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId("Name");

        CashierDTO cashierDTO1 = new CashierDTO();
        cashierDTO1.setName("Name");
        cashierDTO1.setStoreId("42");

        // Act and Assert
        assertFalse(cashierDTO.equals(cashierDTO1));
    }

    @Test
    void testEquals8() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId(null);

        CashierDTO cashierDTO1 = new CashierDTO();
        cashierDTO1.setName("Name");
        cashierDTO1.setStoreId("42");

        // Act and Assert
        assertFalse(cashierDTO.equals(cashierDTO1));
    }

    @Test
    void testEquals9() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName(null);
        cashierDTO.setStoreId("42");

        CashierDTO cashierDTO1 = new CashierDTO();
        cashierDTO1.setName(null);
        cashierDTO1.setStoreId("42");

        // Act and Assert
        assertTrue(cashierDTO.equals(cashierDTO1));
        int expectedHashCodeResult = cashierDTO.hashCode();
        assertEquals(expectedHashCodeResult, cashierDTO1.hashCode());
    }

    @Test
    void testEquals10() {
        // Arrange
        CashierDTO cashierDTO = new CashierDTO();
        cashierDTO.setName("Name");
        cashierDTO.setStoreId(null);

        CashierDTO cashierDTO1 = new CashierDTO();
        cashierDTO1.setName("Name");
        cashierDTO1.setStoreId(null);

        // Act and Assert
        assertTrue(cashierDTO.equals(cashierDTO1));
        int expectedHashCodeResult = cashierDTO.hashCode();
        assertEquals(expectedHashCodeResult, cashierDTO1.hashCode());
    }
}

