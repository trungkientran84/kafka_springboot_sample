package com.t.t.k.ims.model.alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.t.t.k.ims.common.enums.AlertType;
import org.junit.jupiter.api.Test;

class AlertTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        Alert actualAlert = new Alert(AlertType.INVENTORY_THRESHOLD, "Messages");

        // Assert
        assertNull(actualAlert.getCreatedAt());
        assertEquals("Alert(id=null, type=INVENTORY_THRESHOLD, messages=Messages)", actualAlert.toString());
        assertNull(actualAlert.getUpdatedAt());
        assertEquals(AlertType.INVENTORY_THRESHOLD, actualAlert.getType());
        assertEquals("Messages", actualAlert.getMessages());
        assertNull(actualAlert.getId());
    }
}

