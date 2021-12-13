package com.t.t.k.ims.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class KafkaMessageProduceEventTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        KafkaMessageProduceEvent actualKafkaMessageProduceEvent = new KafkaMessageProduceEvent("Topic Name", "Key",
                "Not all who wander are lost");

        // Assert
        assertEquals("Key", actualKafkaMessageProduceEvent.getKey());
        assertEquals("Topic Name", actualKafkaMessageProduceEvent.getTopicName());
        Object expectedMessage = actualKafkaMessageProduceEvent.getSource();
        assertSame(expectedMessage, actualKafkaMessageProduceEvent.getMessage());
    }

    @Test
    void testConstructor2() {
        // Arrange and Act
        KafkaMessageProduceEvent actualKafkaMessageProduceEvent = new KafkaMessageProduceEvent("Topic Name", "Key",
                "Not all who wander are lost", 10L);

        // Assert
        assertEquals("Key", actualKafkaMessageProduceEvent.getKey());
        assertEquals("Topic Name", actualKafkaMessageProduceEvent.getTopicName());
        Object expectedMessage = actualKafkaMessageProduceEvent.getSource();
        assertSame(expectedMessage, actualKafkaMessageProduceEvent.getMessage());
    }
}

