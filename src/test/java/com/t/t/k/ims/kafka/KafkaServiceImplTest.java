package com.t.t.k.ims.kafka;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.repository.kafka.ConsumerFailureRepository;
import com.t.t.k.ims.repository.kafka.ProducerFailureRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {KafkaServiceImpl.class})
@ExtendWith(SpringExtension.class)
class KafkaServiceImplTest {
    @MockBean
    private ConsumerFailureRepository consumerFailureRepository;

    @Autowired
    private KafkaServiceImpl kafkaServiceImpl;

    @MockBean
    private ProducerFailureRepository producerFailureRepository;

    @Test
    void testGetAllConsumerFailureMessages() {
        // Arrange
        PageImpl<ConsumerFailure> pageImpl = new PageImpl<ConsumerFailure>(new ArrayList<ConsumerFailure>());
        when(this.consumerFailureRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<ConsumerFailure> actualAllConsumerFailureMessages = this.kafkaServiceImpl.getAllConsumerFailureMessages(null);

        // Assert
        assertSame(pageImpl, actualAllConsumerFailureMessages);
        assertTrue(actualAllConsumerFailureMessages.toList().isEmpty());
        verify(this.consumerFailureRepository).findAll((org.springframework.data.domain.Pageable) any());
    }

    @Test
    void testGetAllProducerFailureMessages() {
        // Arrange
        PageImpl<ProducerFailure> pageImpl = new PageImpl<ProducerFailure>(new ArrayList<ProducerFailure>());
        when(this.producerFailureRepository.findAll((org.springframework.data.domain.Pageable) any())).thenReturn(pageImpl);

        // Act
        Page<ProducerFailure> actualAllProducerFailureMessages = this.kafkaServiceImpl.getAllProducerFailureMessages(null);

        // Assert
        assertSame(pageImpl, actualAllProducerFailureMessages);
        assertTrue(actualAllProducerFailureMessages.toList().isEmpty());
        verify(this.producerFailureRepository).findAll((org.springframework.data.domain.Pageable) any());
    }
}

