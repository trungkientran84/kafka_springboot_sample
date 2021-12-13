package com.t.t.k.ims.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ConsumerFailureTest {

    @Test
    void testConstructor() {
        // Arrange and Act
        ConsumerFailure actualConsumerFailure = new ConsumerFailure("Record", "Not all who wander are lost",
                "Consumer Class", "An error occurred", "Stack Trace");

        // Assert
        assertEquals("Consumer Class", actualConsumerFailure.getConsumerClass());
        assertEquals("ConsumerFailure(id=null, record=Record, message=Not all who wander are lost, consumerClass=Consumer"
                + " Class, error=An error occurred, stackTrace=Stack Trace)", actualConsumerFailure.toString());
        assertNull(actualConsumerFailure.getUpdatedAt());
        assertEquals("Stack Trace", actualConsumerFailure.getStackTrace());
        assertEquals("Record", actualConsumerFailure.getRecord());
        assertEquals("Not all who wander are lost", actualConsumerFailure.getMessage());
        assertNull(actualConsumerFailure.getId());
        assertEquals("An error occurred", actualConsumerFailure.getError());
        assertNull(actualConsumerFailure.getCreatedAt());
    }

}

