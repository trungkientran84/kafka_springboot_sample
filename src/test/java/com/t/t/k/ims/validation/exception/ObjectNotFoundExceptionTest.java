package com.t.t.k.ims.validation.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ObjectNotFoundExceptionTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        ObjectNotFoundException actualObjectNotFoundException = new ObjectNotFoundException("An error occurred");

        // Assert
        assertNull(actualObjectNotFoundException.getCause());
        assertEquals("com.t.t.k.ims.validation.exception.ObjectNotFoundException: An error occurred",
                actualObjectNotFoundException.toString());
        assertEquals(0, actualObjectNotFoundException.getSuppressed().length);
        assertEquals("An error occurred", actualObjectNotFoundException.getMessage());
        assertEquals("An error occurred", actualObjectNotFoundException.getLocalizedMessage());
    }

    @Test
    void testConstructor2() {
        // Arrange and Act
        ObjectNotFoundException actualObjectNotFoundException = new ObjectNotFoundException("Model Name", "Id");

        // Assert
        assertNull(actualObjectNotFoundException.getCause());
        assertEquals("com.t.t.k.ims.validation.exception.ObjectNotFoundException: Object not found in the"
                + " system. Model Name:Id", actualObjectNotFoundException.toString());
        assertEquals(0, actualObjectNotFoundException.getSuppressed().length);
        assertEquals("Object not found in the system. Model Name:Id", actualObjectNotFoundException.getMessage());
        assertEquals("Object not found in the system. Model Name:Id", actualObjectNotFoundException.getLocalizedMessage());
    }
}

