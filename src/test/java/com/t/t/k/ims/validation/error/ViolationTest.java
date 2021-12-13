package com.t.t.k.ims.validation.error;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ViolationTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        Violation actualViolation = new Violation("Field", "Not all who wander are lost", "42", "Model");

        // Assert
        assertEquals("Field", actualViolation.getField());
        assertEquals("Not all who wander are lost", actualViolation.getMessage());
        assertEquals("Model", actualViolation.getModel());
        assertEquals("42", actualViolation.getRejectedValue());
    }
}

