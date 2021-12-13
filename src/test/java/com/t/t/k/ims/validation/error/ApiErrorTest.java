package com.t.t.k.ims.validation.error;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ApiErrorTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CONTINUE);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertEquals("Continue", actualApiError.getError());
        assertNull(actualApiError.getMessage());
        assertNull(actualApiError.getRequestUri());
        assertEquals(100, actualApiError.getStatus());
        assertEquals("", actualApiError.getTrace());
    }

    @Test
    void testConstructor2() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CONTINUE);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(100, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Continue", actualApiError.getError());
    }

    @Test
    void testConstructor3() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.SWITCHING_PROTOCOLS);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(101, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Switching Protocols", actualApiError.getError());
    }

    @Test
    void testConstructor4() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.PROCESSING);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(102, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Processing", actualApiError.getError());
    }

    @Test
    void testConstructor5() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CHECKPOINT);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(103, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Checkpoint", actualApiError.getError());
    }

    @Test
    void testConstructor6() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.OK);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(200, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("OK", actualApiError.getError());
    }

    @Test
    void testConstructor7() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CREATED);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(201, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Created", actualApiError.getError());
    }

    @Test
    void testConstructor8() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.ACCEPTED);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(202, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Accepted", actualApiError.getError());
    }

    @Test
    void testConstructor9() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.NON_AUTHORITATIVE_INFORMATION);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(203, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Non-Authoritative Information", actualApiError.getError());
    }

    @Test
    void testConstructor10() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.NO_CONTENT);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(204, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("No Content", actualApiError.getError());
    }

    @Test
    void testConstructor11() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.RESET_CONTENT);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(205, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Reset Content", actualApiError.getError());
    }

    @Test
    void testConstructor12() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.PARTIAL_CONTENT);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(206, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Partial Content", actualApiError.getError());
    }

    @Test
    void testConstructor13() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.MULTI_STATUS);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(207, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Multi-Status", actualApiError.getError());
    }

    @Test
    void testConstructor14() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.ALREADY_REPORTED);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(208, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Already Reported", actualApiError.getError());
    }

    @Test
    void testConstructor15() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.IM_USED);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(226, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("IM Used", actualApiError.getError());
    }

    @Test
    void testConstructor16() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.MULTIPLE_CHOICES);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(300, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Multiple Choices", actualApiError.getError());
    }

    @Test
    void testConstructor17() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.MOVED_PERMANENTLY);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(301, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Moved Permanently", actualApiError.getError());
    }

    @Test
    void testConstructor18() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.FOUND);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(302, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Found", actualApiError.getError());
    }

    @Test
    void testConstructor19() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.MOVED_TEMPORARILY);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(302, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Moved Temporarily", actualApiError.getError());
    }

    @Test
    void testConstructor20() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.SEE_OTHER);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(303, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("See Other", actualApiError.getError());
    }

    @Test
    void testConstructor21() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.NOT_MODIFIED);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(304, actualApiError.getStatus());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Not Modified", actualApiError.getError());
    }

    @Test
    void testConstructor22() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CONTINUE, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(100, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Continue", actualApiError.getError());
    }

    @Test
    void testConstructor23() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.SWITCHING_PROTOCOLS, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(101, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Switching Protocols", actualApiError.getError());
    }

    @Test
    void testConstructor24() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.PROCESSING, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(102, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Processing", actualApiError.getError());
    }

    @Test
    void testConstructor25() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CHECKPOINT, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(103, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Checkpoint", actualApiError.getError());
    }

    @Test
    void testConstructor26() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CONTINUE, "Request Uri", "Not all who wander are lost",
                new Throwable(), false);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(100, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Continue", actualApiError.getError());
    }

    @Test
    void testConstructor27() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.OK, "Request Uri", "Not all who wander are lost", new Throwable(),
                true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(200, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("OK", actualApiError.getError());
    }

    @Test
    void testConstructor28() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CONTINUE, "Request Uri", "Not all who wander are lost",
                new Throwable(), false);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(100, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Continue", actualApiError.getError());
    }

    @Test
    void testConstructor29() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CREATED, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(201, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Created", actualApiError.getError());
    }

    @Test
    void testConstructor30() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.ACCEPTED, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(202, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Accepted", actualApiError.getError());
    }

    @Test
    void testConstructor31() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.NON_AUTHORITATIVE_INFORMATION, "Request Uri",
                "Not all who wander are lost", new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(203, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Non-Authoritative Information", actualApiError.getError());
    }

    @Test
    void testConstructor32() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.NO_CONTENT, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(204, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("No Content", actualApiError.getError());
    }

    @Test
    void testConstructor33() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.RESET_CONTENT, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(205, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Reset Content", actualApiError.getError());
    }

    @Test
    void testConstructor34() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.PARTIAL_CONTENT, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(206, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Partial Content", actualApiError.getError());
    }

    @Test
    void testConstructor35() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.MULTI_STATUS, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(207, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Multi-Status", actualApiError.getError());
    }

    @Test
    void testConstructor36() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.ALREADY_REPORTED, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(208, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Already Reported", actualApiError.getError());
    }

    @Test
    void testConstructor37() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.IM_USED, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(226, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("IM Used", actualApiError.getError());
    }

    @Test
    void testConstructor38() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.MULTIPLE_CHOICES, "Request Uri", "Not all who wander are lost",
                new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(300, actualApiError.getStatus());
        assertEquals("Request Uri", actualApiError.getRequestUri());
        assertEquals("Not all who wander are lost", actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Multiple Choices", actualApiError.getError());
    }

    @Test
    void testConstructor39() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CONTINUE, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(100, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Continue", actualApiError.getError());
    }

    @Test
    void testConstructor40() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.SWITCHING_PROTOCOLS, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(101, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Switching Protocols", actualApiError.getError());
    }

    @Test
    void testConstructor41() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.PROCESSING, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(102, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Processing", actualApiError.getError());
    }

    @Test
    void testConstructor42() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CHECKPOINT, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(103, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Checkpoint", actualApiError.getError());
    }

    @Test
    void testConstructor43() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CONTINUE, new Throwable(), false);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(100, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Continue", actualApiError.getError());
    }

    @Test
    void testConstructor44() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.OK, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(200, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("OK", actualApiError.getError());
    }

    @Test
    void testConstructor45() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CONTINUE, new Throwable(), false);

        // Assert
        assertEquals("", actualApiError.getTrace());
        assertEquals(100, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Continue", actualApiError.getError());
    }

    @Test
    void testConstructor46() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.CREATED, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(201, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Created", actualApiError.getError());
    }

    @Test
    void testConstructor47() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.ACCEPTED, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(202, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Accepted", actualApiError.getError());
    }

    @Test
    void testConstructor48() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.NON_AUTHORITATIVE_INFORMATION, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(203, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Non-Authoritative Information", actualApiError.getError());
    }

    @Test
    void testConstructor49() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.NO_CONTENT, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(204, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("No Content", actualApiError.getError());
    }

    @Test
    void testConstructor50() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.RESET_CONTENT, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(205, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Reset Content", actualApiError.getError());
    }

    @Test
    void testConstructor51() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.PARTIAL_CONTENT, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(206, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Partial Content", actualApiError.getError());
    }

    @Test
    void testConstructor52() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.MULTI_STATUS, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(207, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Multi-Status", actualApiError.getError());
    }

    @Test
    void testConstructor53() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.ALREADY_REPORTED, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(208, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Already Reported", actualApiError.getError());
    }

    @Test
    void testConstructor54() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.IM_USED, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(226, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("IM Used", actualApiError.getError());
    }

    @Test
    void testConstructor55() {
        // Arrange and Act
        ApiError actualApiError = new ApiError(HttpStatus.MULTIPLE_CHOICES, new Throwable(), true);

        // Assert
        assertNull(actualApiError.getDebugMessage());
        assertTrue(!actualApiError.getTrace().isEmpty());
        assertEquals(300, actualApiError.getStatus());
        assertNull(actualApiError.getMessage());
        assertTrue(actualApiError.getInfo().isEmpty());
        assertEquals("Multiple Choices", actualApiError.getError());
    }
}

