package com.t.t.k.ims.common.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PaymentMethodTest {
    @Test
    void testValueOf() {
        // Arrange, Act and Assert
        assertEquals(PaymentMethod.CASH, PaymentMethod.valueOf("CASH"));
        assertEquals(PaymentMethod.CREDIT_CARD, PaymentMethod.valueOf("CREDIT_CARD"));
        assertEquals(PaymentMethod.DEBIT_CARD, PaymentMethod.valueOf("DEBIT_CARD"));
        assertEquals(PaymentMethod.GIFT_CARD, PaymentMethod.valueOf("GIFT_CARD"));
        assertEquals(PaymentMethod.CASH, PaymentMethod.valueOf("CASH"));
        assertEquals(PaymentMethod.CREDIT_CARD, PaymentMethod.valueOf("CREDIT_CARD"));
        assertEquals(PaymentMethod.DEBIT_CARD, PaymentMethod.valueOf("DEBIT_CARD"));
        assertEquals(PaymentMethod.GIFT_CARD, PaymentMethod.valueOf("GIFT_CARD"));
    }

    @Test
    void testValues() {
        // Arrange and Act
        PaymentMethod[] actualValuesResult = PaymentMethod.values();

        // Assert
        assertEquals(4, actualValuesResult.length);
        assertEquals(PaymentMethod.CASH, actualValuesResult[0]);
        assertEquals(PaymentMethod.CREDIT_CARD, actualValuesResult[1]);
        assertEquals(PaymentMethod.DEBIT_CARD, actualValuesResult[2]);
        assertEquals(PaymentMethod.GIFT_CARD, actualValuesResult[3]);
    }

    @Test
    void testValues2() {
        // Arrange and Act
        PaymentMethod[] actualValuesResult = PaymentMethod.values();

        // Assert
        assertEquals(4, actualValuesResult.length);
        assertEquals(PaymentMethod.CASH, actualValuesResult[0]);
        assertEquals(PaymentMethod.CREDIT_CARD, actualValuesResult[1]);
        assertEquals(PaymentMethod.DEBIT_CARD, actualValuesResult[2]);
        assertEquals(PaymentMethod.GIFT_CARD, actualValuesResult[3]);
    }
}

