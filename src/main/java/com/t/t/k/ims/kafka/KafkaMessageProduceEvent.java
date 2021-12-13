package com.t.t.k.ims.kafka;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * The class carries the topic name, key and message need to send to Kafka server
 *
 * @author ttkien
 */

@Getter
public class KafkaMessageProduceEvent extends ApplicationEvent {
    private final String topicName;
    private final String key;
    private final String message;

    @PersistenceConstructor
    public KafkaMessageProduceEvent(String topicName, String key, String message, long timestamp) {
        this(topicName, key, message);
    }

    public KafkaMessageProduceEvent(String topicName, String key, String message) {
        super(message);
        this.topicName = topicName;
        this.key = key;
        this.message = message;
    }

}
