package com.t.t.k.ims.model.refunds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.t.t.k.ims.common.enums.PaymentMethod;
import com.t.t.k.ims.common.enums.RefundStatus;
import com.t.t.k.ims.model.orders.Payment;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class RefundTest {
    @Test
    void testConstructor() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);
        Payment payment = new Payment(PaymentMethod.CASH, valueOfResult);

        // Act
        Refund actualRefund = new Refund("adfiajdofaf",payment, RefundStatus.OPEN);

        // Assert
        assertNull(actualRefund.getCreatedAt());
        assertNull(actualRefund.getUpdatedAt());
        assertEquals(RefundStatus.OPEN, actualRefund.getStatus());
        Payment payment1 = actualRefund.getPayment();
        assertSame(payment, payment1);
        assertNull(actualRefund.getId());
        assertEquals(PaymentMethod.CASH, payment1.getPaymentMethod());
        BigDecimal expectedAmount = valueOfResult.ONE;
        BigDecimal amount = payment1.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(0, amount.scale());
        assertEquals("1", amount.toString());
        assertEquals(1, amount.signum());
        assertSame(payment1, payment);
    }
}

