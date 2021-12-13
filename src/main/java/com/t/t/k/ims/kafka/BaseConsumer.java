package com.t.t.k.ims.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Arrays;

/**
 * The abstract class to for message consumer which handle the failure of inherent classes when it processes a message
 * Basically, it will save the information about failure to data store and also produce a message about the failure
 * to kafka server in order for other consumers to handle
 *
 * @author ttkien
 */
public abstract class BaseConsumer {

    @Getter
    private static final Logger logger = LoggerFactory.getLogger(BaseConsumer.class);
    @Getter
    private final CrudRepository<ConsumerFailure, String> repository;
    @Getter
    private final ApplicationEventMulticaster eventPublisher;

    @Value("${app.config.kafka.topics.consumer-failure}")
    private String TOPIC;

    /**
     * @param repository     the repository to save the failure messages
     * @param eventPublisher the publisher to produce failure messages
     */
    protected BaseConsumer(CrudRepository<ConsumerFailure, String> repository, ApplicationEventMulticaster eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * The inherited class need to implement this method to consume a message
     *
     * @param record  the kafka consumer record
     * @param message the kafka message payload
     * @param ack     the kafka acknowledgment
     * @throws Exception if error
     */
    public abstract void listen(ConsumerRecord<?, ?> record, @Payload String message, Acknowledgment ack) throws Exception;

    /**
     * Save the information on failure of consumer process
     *
     * @param record the kafka record
     * @param s      the kafka message
     * @param c      the consumer class
     * @throws Exception if any error
     */
    protected void onFail(ConsumerRecord<?, ?> record, String s, Class<?> c, Throwable ex) throws Exception {
        ConsumerFailure o = new ConsumerFailure(record.toString(), s, c.getName(), ex.toString(), Arrays.toString(ex.getStackTrace()));
        eventPublisher.multicastEvent(new KafkaMessageProduceEvent(TOPIC, (String) record.key(), (new ObjectMapper()).writeValueAsString(o)));
        repository.save(o);

    }

    /**
     * Process a consumer record
     *
     * @param record  the kafka consumer record
     * @param message the kafka message payload
     * @param ack     the kafka acknowledgment
     * @throws Exception if there is error
     */
    protected void process(ConsumerRecord<?, ?> record, @Payload String message, Acknowledgment ack) throws Exception {
        try {

            ack.acknowledge();

            logger.debug(String.format("Consuming message -> %s", message));

            process(message);

        } catch (Exception ex) {
            onFail(record, message, this.getClass(), ex);
            logger.error(String.format("Kafka consumer failed to consume a record. Record: {%s})", record), ex);
        }
    }

    /**
     * let inherited class to decide how to process the message
     *
     * @param s the message
     * @throws Exception if error
     */
    protected abstract void process(String s) throws Exception;
}
