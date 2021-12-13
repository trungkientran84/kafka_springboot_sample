package com.t.t.k.ims.validation.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ValidationErrorResponseTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.CONTINUE,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);
        ArrayList<Violation> violationList = new ArrayList<Violation>();
        actualValidationErrorResponse.setViolations(violationList);

        // Assert
        assertSame(violationList, actualValidationErrorResponse.getViolations());
    }

    @Test
    void testConstructor2() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.CONTINUE,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());;
        assertEquals(100, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Continue", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor3() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.SWITCHING_PROTOCOLS,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());;
        assertEquals(101, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Switching Protocols", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor4() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.PROCESSING,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());;
        assertEquals(102, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Processing", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor5() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.CHECKPOINT,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(103, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Checkpoint", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor6() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.CONTINUE,
                "Request Uri", "Not all who wander are lost", new Throwable(), false);

        // Assert
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertEquals("", actualValidationErrorResponse.getTrace());
        assertEquals(100, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Continue", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor7() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.OK, "Request Uri",
                "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());;
        assertEquals(200, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("OK", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor8() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.CONTINUE,
                "Request Uri", "Not all who wander are lost", new Throwable(), false);

        // Assert
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertEquals("", actualValidationErrorResponse.getTrace());
        assertEquals(100, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Continue", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor9() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.CREATED,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());;
        assertEquals(201, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Created", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor10() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.ACCEPTED,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(202, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Accepted", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor11() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(
                HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(203, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Non-Authoritative Information", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor12() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.NO_CONTENT,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(204, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("No Content", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor13() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.RESET_CONTENT,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(205, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Reset Content", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor14() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.PARTIAL_CONTENT,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(206, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Partial Content", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor15() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.MULTI_STATUS,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(207, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Multi-Status", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor16() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.ALREADY_REPORTED,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(208, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Already Reported", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor17() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.IM_USED,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(226, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("IM Used", actualValidationErrorResponse.getError());
    }

    @Test
    void testConstructor18() {
        // Arrange and Act
        ValidationErrorResponse actualValidationErrorResponse = new ValidationErrorResponse(HttpStatus.MULTIPLE_CHOICES,
                "Request Uri", "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualValidationErrorResponse.getDebugMessage());
        assertTrue(actualValidationErrorResponse.getViolations().isEmpty());
        assertTrue(!actualValidationErrorResponse.getTrace().isEmpty());
        assertEquals(300, actualValidationErrorResponse.getStatus());
        assertEquals("Request Uri", actualValidationErrorResponse.getRequestUri());
        assertEquals("Not all who wander are lost", actualValidationErrorResponse.getMessage());
        assertTrue(actualValidationErrorResponse.getInfo().isEmpty());
        assertEquals("Multiple Choices", actualValidationErrorResponse.getError());
    }
}

