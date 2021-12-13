package com.t.t.k.ims.kafka;

import com.t.t.k.ims.model.common.BasedModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * This model stores all information about a failure of a producer when it produced a kafka message
 * The information in this will be used for auditing and restart produce the message if necessary
 *
 * @author ttkien
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "KafkaProducerFailure")
public class ProducerFailure extends BasedModel {

    @Id
    private String id;

    @NotNull
    KafkaMessageProduceEvent event;

    @NotNull
    String error;

    StackTraceElement[] stackTrace;

    public ProducerFailure(KafkaMessageProduceEvent event, String error, StackTraceElement[] stackTrace) {
        this.event = event;
        this.error = error;
        this.stackTrace = stackTrace;
    }
}
