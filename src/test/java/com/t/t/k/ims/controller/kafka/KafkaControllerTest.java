package com.t.t.k.ims.controller.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.kafka.ConsumerFailure;
import com.t.t.k.ims.kafka.KafkaService;
import com.t.t.k.ims.kafka.KafkaServiceImpl;
import com.t.t.k.ims.kafka.ProducerFailure;
import com.t.t.k.ims.repository.kafka.ConsumerFailureRepository;
import com.t.t.k.ims.repository.kafka.ProducerFailureRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class KafkaControllerTest {
    @Test
    void testConstructor() {
        // Arrange
        KafkaServiceImpl kafkaServiceImpl = new KafkaServiceImpl(mock(ConsumerFailureRepository.class),
                mock(ProducerFailureRepository.class));

        // Act and Assert
        KafkaService kafkaService = (new KafkaController(kafkaServiceImpl)).kafkaService;
        assertTrue(kafkaService instanceof KafkaServiceImpl);
        assertSame(kafkaService, kafkaServiceImpl);
    }

    @Test
    void testGetAllConsumerFailureMessages() throws Exception {
        // Arrange
        ConsumerFailureRepository consumerFailureRepository = mock(ConsumerFailureRepository.class);
        when(consumerFailureRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<ConsumerFailure>(new ArrayList<ConsumerFailure>()));

        // Act
        ResponseEntity<Page<ConsumerFailure>> actualAllConsumerFailureMessages = (new KafkaController(
                new KafkaServiceImpl(consumerFailureRepository, mock(ProducerFailureRepository.class))))
                .getAllConsumerFailureMessages(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>",
                actualAllConsumerFailureMessages.toString());
        assertTrue(actualAllConsumerFailureMessages.getBody().toList().isEmpty());
        assertTrue(actualAllConsumerFailureMessages.hasBody());
        assertEquals(HttpStatus.OK, actualAllConsumerFailureMessages.getStatusCode());
        assertTrue(actualAllConsumerFailureMessages.getHeaders().isEmpty());
        verify(consumerFailureRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAllProducerFailureMessages() throws Exception {
        // Arrange
        ProducerFailureRepository producerFailureRepository = mock(ProducerFailureRepository.class);
        when(producerFailureRepository.findAll((org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<ProducerFailure>(new ArrayList<ProducerFailure>()));

        // Act
        ResponseEntity<Page<ProducerFailure>> actualAllProducerFailureMessages = (new KafkaController(
                new KafkaServiceImpl(mock(ConsumerFailureRepository.class), producerFailureRepository)))
                .getAllProducerFailureMessages(null);

        // Assert
        assertEquals("<200 OK OK,Page 1 of 1 containing UNKNOWN instances,[]>",
                actualAllProducerFailureMessages.toString());
        assertTrue(actualAllProducerFailureMessages.getBody().toList().isEmpty());
        assertTrue(actualAllProducerFailureMessages.hasBody());
        assertEquals(HttpStatus.OK, actualAllProducerFailureMessages.getStatusCode());
        assertTrue(actualAllProducerFailureMessages.getHeaders().isEmpty());
        verify(producerFailureRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}

