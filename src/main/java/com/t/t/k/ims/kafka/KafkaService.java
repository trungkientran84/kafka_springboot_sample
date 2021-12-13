package com.t.t.k.ims.kafka;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * This interface defines all business methods available on Kafka Service
 *
 * @author ttkien
 */
public interface KafkaService {

    /**
     * Get all consumer failure messages.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of consumer failure messages.
     */
    Page<ConsumerFailure> getAllConsumerFailureMessages(Pageable pageable) throws Exception;

    /**
     * Get all producer failure messages.
     *
     * @param pageable The object contains pageable information
     * @return the pageable list of producer failure messages
     */
    Page<ProducerFailure> getAllProducerFailureMessages(Pageable pageable) throws Exception;

}
