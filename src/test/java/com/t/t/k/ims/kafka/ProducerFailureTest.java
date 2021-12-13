package com.t.t.k.ims.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ProducerFailureTest {
    @Test
    void testConstructor() {
        // Arrange
        KafkaMessageProduceEvent kafkaMessageProduceEvent = new KafkaMessageProduceEvent("Topic Name", "Key",
                "Not all who wander are lost");

        StackTraceElement[] stackTraceElementArray = new StackTraceElement[]{new StackTraceElement("foo", "foo", "foo", 1)};

        // Act
        ProducerFailure actualProducerFailure = new ProducerFailure(kafkaMessageProduceEvent, "An error occurred",
                stackTraceElementArray);

        // Assert
        assertNull(actualProducerFailure.getCreatedAt());
        assertEquals(
                "ProducerFailure(id=null, event=com.t.t.k.ims.kafka.KafkaMessageProduceEvent[source=Not"
                        + " all who wander are lost], error=An error occurred, stackTrace=[foo.foo(foo:1)])",
                actualProducerFailure.toString());
        assertNull(actualProducerFailure.getUpdatedAt());
        StackTraceElement[] expectedStackTrace = actualProducerFailure.stackTrace;
        StackTraceElement[] stackTrace = actualProducerFailure.getStackTrace();
        assertSame(expectedStackTrace, stackTrace);
        assertEquals(1, stackTrace.length);
        assertNull(actualProducerFailure.getId());
        KafkaMessageProduceEvent expectedEvent = actualProducerFailure.event;
        KafkaMessageProduceEvent event = actualProducerFailure.getEvent();
        assertSame(expectedEvent, event);
        assertEquals("An error occurred", actualProducerFailure.getError());
        assertEquals("com.t.t.k.ims.kafka.KafkaMessageProduceEvent[source=Not all who wander are lost]",
                event.toString());
        assertEquals("Topic Name", event.getTopicName());
        Object source = event.getSource();
        assertEquals("Not all who wander are lost", source);
        assertSame(source, event.getMessage());
        assertEquals("Key", event.getKey());
        assertSame(event, kafkaMessageProduceEvent);
        assertSame(stackTrace, stackTraceElementArray);
    }
}

