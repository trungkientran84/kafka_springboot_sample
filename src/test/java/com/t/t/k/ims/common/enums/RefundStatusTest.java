package com.t.t.k.ims.common.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RefundStatusTest {
    @Test
    void testValueOf() {
        // Arrange, Act and Assert
        assertEquals(RefundStatus.CLOSED, RefundStatus.valueOf("CLOSED"));
        assertEquals(RefundStatus.OPEN, RefundStatus.valueOf("OPEN"));
        assertEquals(RefundStatus.PROCESSING, RefundStatus.valueOf("PROCESSING"));
    }

    @Test
    void testValues() {
        // Arrange and Act
        RefundStatus[] actualValuesResult = RefundStatus.values();

        // Assert
        assertEquals(3, actualValuesResult.length);
        assertEquals(RefundStatus.OPEN, actualValuesResult[0]);
        assertEquals(RefundStatus.PROCESSING, actualValuesResult[1]);
        assertEquals(RefundStatus.CLOSED, actualValuesResult[2]);
    }
}

