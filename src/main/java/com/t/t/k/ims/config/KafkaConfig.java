package com.t.t.k.ims.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * Configuration for creating kafka topics if it is not exist
 *
 * @author ttkien
 */
@Configuration
public class KafkaConfig {

    @Value("${app.config.kafka.topics.inventory}")
    private String INVENTORY_TOPIC;

    @Value("${app.config.kafka.topics.order}")
    private String ORDER_TOPIC;

    @Value("${app.config.kafka.topics.consumer-failure}")
    private String CONSUMER_FAILURE_TOPIC;

    @Bean
    public KafkaAdmin.NewTopics topics456() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(INVENTORY_TOPIC)
                        .build(),
                TopicBuilder.name(ORDER_TOPIC)
                        .build(),
                TopicBuilder.name(CONSUMER_FAILURE_TOPIC)
                        .build());
    }


}