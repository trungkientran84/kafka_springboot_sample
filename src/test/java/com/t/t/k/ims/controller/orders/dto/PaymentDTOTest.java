package com.t.t.k.ims.controller.orders.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.t.t.k.ims.common.enums.PaymentMethod;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PaymentDTOTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        PaymentDTO actualPaymentDTO = new PaymentDTO();
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        actualPaymentDTO.setAmount(valueOfResult);
        actualPaymentDTO.setPaymentMethod(PaymentMethod.CASH);

        // Assert
        BigDecimal expectedAmount = valueOfResult.ONE;
        assertSame(expectedAmount, actualPaymentDTO.getAmount());
        assertEquals(PaymentMethod.CASH, actualPaymentDTO.getPaymentMethod());
        assertEquals("PaymentDTO(paymentMethod=CASH, amount=1)", actualPaymentDTO.toString());
    }

    @Test
    void testEquals() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertFalse(paymentDTO.equals(null));
    }

    @Test
    void testEquals2() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertFalse(paymentDTO.equals("Different type to PaymentDTO"));
    }

    @Test
    void testEquals3() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertTrue(paymentDTO.equals(paymentDTO));
        int expectedHashCodeResult = paymentDTO.hashCode();
        assertEquals(expectedHashCodeResult, paymentDTO.hashCode());
    }

    @Test
    void testEquals4() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);

        PaymentDTO paymentDTO1 = new PaymentDTO();
        paymentDTO1.setAmount(BigDecimal.valueOf(1L));
        paymentDTO1.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertTrue(paymentDTO.equals(paymentDTO1));
        int expectedHashCodeResult = paymentDTO.hashCode();
        assertEquals(expectedHashCodeResult, paymentDTO1.hashCode());
    }

    @Test
    void testEquals5() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(0L));
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);

        PaymentDTO paymentDTO1 = new PaymentDTO();
        paymentDTO1.setAmount(BigDecimal.valueOf(1L));
        paymentDTO1.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertFalse(paymentDTO.equals(paymentDTO1));
    }

    @Test
    void testEquals6() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(null);
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);

        PaymentDTO paymentDTO1 = new PaymentDTO();
        paymentDTO1.setAmount(BigDecimal.valueOf(1L));
        paymentDTO1.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertFalse(paymentDTO.equals(paymentDTO1));
    }

    @Test
    void testEquals7() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        paymentDTO.setPaymentMethod(null);

        PaymentDTO paymentDTO1 = new PaymentDTO();
        paymentDTO1.setAmount(BigDecimal.valueOf(1L));
        paymentDTO1.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertFalse(paymentDTO.equals(paymentDTO1));
    }

    @Test
    void testEquals8() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        paymentDTO.setPaymentMethod(PaymentMethod.CREDIT_CARD);

        PaymentDTO paymentDTO1 = new PaymentDTO();
        paymentDTO1.setAmount(BigDecimal.valueOf(1L));
        paymentDTO1.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertFalse(paymentDTO.equals(paymentDTO1));
    }

    @Test
    void testEquals9() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(null);
        paymentDTO.setPaymentMethod(PaymentMethod.CASH);

        PaymentDTO paymentDTO1 = new PaymentDTO();
        paymentDTO1.setAmount(null);
        paymentDTO1.setPaymentMethod(PaymentMethod.CASH);

        // Act and Assert
        assertTrue(paymentDTO.equals(paymentDTO1));
        int expectedHashCodeResult = paymentDTO.hashCode();
        assertEquals(expectedHashCodeResult, paymentDTO1.hashCode());
    }

    @Test
    void testEquals10() {
        // Arrange
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(BigDecimal.valueOf(1L));
        paymentDTO.setPaymentMethod(null);

        PaymentDTO paymentDTO1 = new PaymentDTO();
        paymentDTO1.setAmount(BigDecimal.valueOf(1L));
        paymentDTO1.setPaymentMethod(null);

        // Act and Assert
        assertTrue(paymentDTO.equals(paymentDTO1));
        int expectedHashCodeResult = paymentDTO.hashCode();
        assertEquals(expectedHashCodeResult, paymentDTO1.hashCode());
    }
}

