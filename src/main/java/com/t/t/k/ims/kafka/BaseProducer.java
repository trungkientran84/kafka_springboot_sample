package com.t.t.k.ims.kafka;

import com.t.t.k.ims.repository.kafka.ProducerFailureRepository;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


/**
 * The kafka producer to produce the kafka message based on kafka template
 *
 * @author ttkien
 */

@Service
@Getter
public class BaseProducer {
    private final Logger logger = LoggerFactory.getLogger(BaseProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ProducerFailureRepository repository;


    @Autowired
    public BaseProducer(KafkaTemplate<String, String> kafkaTemplate, ProducerFailureRepository repository) {
        this.kafkaTemplate = kafkaTemplate;
        this.repository = repository;
    }

    /**
     * Produce the message in provided event to kafka server
     *
     * @param event the object contains message and target topic
     */
    public void sendMessage(KafkaMessageProduceEvent event) {

        logger.debug(String.format("Producing message -> %s", event));
        try {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(event.getTopicName(), event.getKey(), event.getMessage());

            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onSuccess(SendResult<String, String> result) {
                    logger.debug("Successfully sent message=[" + result.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                }

                @Override
                public void onFailure(Throwable ex) {
                    processFailure(event, ex);

                }
            });
        } catch (Throwable ex) {
            processFailure(event, ex);
        }
    }

    /**
     * Store the failure reason to database
     *
     * @param event the original event
     * @param ex    the error
     */
    private void processFailure(KafkaMessageProduceEvent event, Throwable ex) {
        logger.debug(String.format("Unable to send message=[%s]", event), ex);
        repository.save(new ProducerFailure(event, ex.toString(), ex.getStackTrace()));
    }

}
