package com.t.t.k.ims.kafka;


import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Listener for kafka producer events
 *
 * @author ttkien
 */

@Component
@Getter
public class KafkaEventListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BaseProducer producer;

    public KafkaEventListener(BaseProducer producer) {
        this.producer = producer;
    }

    @EventListener
    public void produceKafkaMessage(KafkaMessageProduceEvent event) {
        logger.debug("On kafka producer events message=[" + event + "]");
        producer.sendMessage(event);
    }
}
