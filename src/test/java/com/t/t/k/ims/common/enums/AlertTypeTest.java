package com.t.t.k.ims.common.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AlertTypeTest {
    @Test
    void testValueOf() {
        // Arrange, Act and Assert
        assertEquals(AlertType.INVENTORY_THRESHOLD, AlertType.valueOf("INVENTORY_THRESHOLD"));
        assertEquals(AlertType.OTHER, AlertType.valueOf("OTHER"));
    }

    @Test
    void testValues() {
        // Arrange and Act
        AlertType[] actualValuesResult = AlertType.values();

        // Assert
        assertEquals(2, actualValuesResult.length);
        assertEquals(AlertType.INVENTORY_THRESHOLD, actualValuesResult[0]);
        assertEquals(AlertType.OTHER, actualValuesResult[1]);
    }
}

