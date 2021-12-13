package com.t.t.k.ims.model.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InventoryTest {
    @Test
    void testDecrease() {
        // Arrange
        Inventory inventory = new Inventory();
        inventory.setCount(3);

        // Act
        inventory.decrease(10);

        // Assert
        assertEquals(-7, inventory.getCount().intValue());
    }

    @Test
    void testIncrease() {
        // Arrange
        Inventory inventory = new Inventory();
        inventory.setCount(3);

        // Act
        inventory.increase(10);

        // Assert
        assertEquals(13, inventory.getCount().intValue());
    }
}

