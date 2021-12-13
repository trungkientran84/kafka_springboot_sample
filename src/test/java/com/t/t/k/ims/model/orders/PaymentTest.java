package com.t.t.k.ims.model.orders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.t.t.k.ims.common.enums.PaymentMethod;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PaymentTest {
    @Test
    void testConstructor() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);

        // Act
        Payment actualPayment = new Payment("42", PaymentMethod.CASH, valueOfResult);

        // Assert
        BigDecimal expectedAmount = valueOfResult.ONE;
        BigDecimal amount = actualPayment.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(PaymentMethod.CASH, actualPayment.getPaymentMethod());
        assertEquals("42", actualPayment.getId());
        assertEquals(1, amount.signum());
        assertEquals(0, amount.scale());
        assertEquals("1", amount.toString());
        assertSame(amount, valueOfResult);
    }

    @Test
    void testConstructor2() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);

        // Act
        Payment actualPayment = new Payment(PaymentMethod.CASH, valueOfResult);

        // Assert
        BigDecimal expectedAmount = valueOfResult.ONE;
        BigDecimal amount = actualPayment.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(PaymentMethod.CASH, actualPayment.getPaymentMethod());
        assertEquals("1", amount.toString());
    }

    @Test
    void testConstructor3() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);

        // Act
        Payment actualPayment = new Payment(PaymentMethod.CREDIT_CARD, valueOfResult);

        // Assert
        BigDecimal expectedAmount = valueOfResult.ONE;
        BigDecimal amount = actualPayment.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(PaymentMethod.CREDIT_CARD, actualPayment.getPaymentMethod());
        assertEquals("1", amount.toString());
    }

    @Test
    void testConstructor4() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);

        // Act
        Payment actualPayment = new Payment(PaymentMethod.DEBIT_CARD, valueOfResult);

        // Assert
        BigDecimal expectedAmount = valueOfResult.ONE;
        BigDecimal amount = actualPayment.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(PaymentMethod.DEBIT_CARD, actualPayment.getPaymentMethod());
        assertEquals("1", amount.toString());
    }

    @Test
    void testConstructor5() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);

        // Act
        Payment actualPayment = new Payment(PaymentMethod.GIFT_CARD, valueOfResult);

        // Assert
        BigDecimal expectedAmount = valueOfResult.ONE;
        BigDecimal amount = actualPayment.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(PaymentMethod.GIFT_CARD, actualPayment.getPaymentMethod());
        assertEquals("1", amount.toString());
    }
}

