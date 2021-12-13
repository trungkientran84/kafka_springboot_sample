package com.t.t.k.ims.kafka;

import com.t.t.k.ims.repository.kafka.ConsumerFailureRepository;
import com.t.t.k.ims.repository.kafka.ProducerFailureRepository;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * This interface defines all business methods available on Kafka Service
 *
 * @author ttkien
 */
@Service
@Getter
public class KafkaServiceImpl implements KafkaService {

    private final ConsumerFailureRepository consumerFailureRepository;
    private final ProducerFailureRepository producerFailureRepository;

    /**
     * Constructor to initialize an instance of this class
     *
     * @param consumerFailureRepository the dependent repository
     * @param producerFailureRepository he dependent repository
     */
    public KafkaServiceImpl(ConsumerFailureRepository consumerFailureRepository, ProducerFailureRepository producerFailureRepository) {
        this.consumerFailureRepository = consumerFailureRepository;
        this.producerFailureRepository = producerFailureRepository;
    }

    /**
     * Get all consumer failure messages.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of consumer failure messages.
     */
    @Override
    public Page<ConsumerFailure> getAllConsumerFailureMessages(Pageable pageable) {
        return consumerFailureRepository.findAll(pageable);
    }

    /**
     * Get all producer failure messages.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of producer failure messages
     */
    @Override
    public Page<ProducerFailure> getAllProducerFailureMessages(Pageable pageable) {
        return producerFailureRepository.findAll(pageable);
    }
}
