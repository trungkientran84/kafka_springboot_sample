package com.t.t.k.ims.kafka;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.repository.kafka.ProducerFailureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BaseProducer.class})
@DirtiesContext
@ExtendWith(SpringExtension.class)
class BaseProducerTest {

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;


    @Autowired
    private BaseProducer baseProducer;

    @MockBean
    private ProducerFailureRepository producerFailureRepository;

    @Test
    void testSendMessage() {
        // Arrange
        KafkaMessageProduceEvent event = new KafkaMessageProduceEvent("Topic Name", "Key", "Not all who wander are lost");

        StackTraceElement stackTraceElement = new StackTraceElement("foo", "foo", "foo", 1);

        StackTraceElement stackTraceElement1 = new StackTraceElement("foo", "foo", "foo", 1);

        when(this.producerFailureRepository.save((ProducerFailure) any())).thenReturn(new ProducerFailure(event,
                "An error occurred",
                new StackTraceElement[]{stackTraceElement, stackTraceElement1, new StackTraceElement("foo", "foo", "foo", 1)}));

        // Act
        this.baseProducer.sendMessage(new KafkaMessageProduceEvent("Topic Name", "Key", "Not all who wander are lost"));

        // Assert
        verify(this.producerFailureRepository).save((ProducerFailure) any());
    }

    @Test
    void testSendMessage2() {

        // Arrange
        KafkaMessageProduceEvent event = new KafkaMessageProduceEvent("Topic Name", "Key", "Not all who wander are lost");

        StackTraceElement stackTraceElement = new StackTraceElement("foo", "foo", "foo", 1);

        StackTraceElement stackTraceElement1 = new StackTraceElement("foo", "foo", "foo", 1);

        when(this.producerFailureRepository.save((ProducerFailure) any())).thenReturn(new ProducerFailure(event,
                "An error occurred",
                new StackTraceElement[]{stackTraceElement, stackTraceElement1, new StackTraceElement("foo", "foo", "foo", 1)}));

        // Act
        this.baseProducer.sendMessage(null);
    }

    @Test
    void testSendMessage3() {
        // Arrange
        KafkaMessageProduceEvent event = new KafkaMessageProduceEvent("Topic Name", "Key", "Not all who wander are lost");

        StackTraceElement stackTraceElement = new StackTraceElement("foo", "foo", "foo", 1);

        StackTraceElement stackTraceElement1 = new StackTraceElement("foo", "foo", "foo", 1);

        when(this.producerFailureRepository.save((ProducerFailure) any())).thenReturn(new ProducerFailure(event,
                "An error occurred",
                new StackTraceElement[]{stackTraceElement, stackTraceElement1, new StackTraceElement("foo", "foo", "foo", 1)}));

        // Act
        this.baseProducer.sendMessage(new KafkaMessageProduceEvent("Topic Name", "Key", "Not all who wander are lost"));

        // Assert
        verify(this.producerFailureRepository).save((ProducerFailure) any());
    }

    @Test
    void testSendMessage4() {
        // Arrange
        KafkaMessageProduceEvent event = new KafkaMessageProduceEvent("Topic Name", "Key", "Not all who wander are lost");

        StackTraceElement stackTraceElement = new StackTraceElement("foo", "foo", "foo", 1);

        StackTraceElement stackTraceElement1 = new StackTraceElement("foo", "foo", "foo", 1);

        when(this.producerFailureRepository.save((ProducerFailure) any())).thenReturn(new ProducerFailure(event,
                "An error occurred",
                new StackTraceElement[]{stackTraceElement, stackTraceElement1, new StackTraceElement("foo", "foo", "foo", 1)}));

        // Act
        this.baseProducer.sendMessage(null);

        // Assert
        verify(this.producerFailureRepository).save((ProducerFailure) any());
    }
}

