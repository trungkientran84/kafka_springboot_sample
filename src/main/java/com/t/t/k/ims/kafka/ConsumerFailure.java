package com.t.t.k.ims.kafka;

import com.t.t.k.ims.model.common.BasedModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * This model stores all information about a processing failure of a consumer when it consumed a kafka message
 * The information in this will be used for auditing and restart processing the message
 *
 * @author ttkien
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "KafkaConsumerFailure")
public class ConsumerFailure extends BasedModel {

    public ConsumerFailure(String record, String message, String consumerClass, String error, String stackTrace) {
        this.record = record;
        this.message = message;
        this.consumerClass = consumerClass;
        this.error = error;
        this.stackTrace = stackTrace;
    }

    @Id
    private String id;

    @NotNull
    String record;

    @NotNull
    String message;

    @NotNull
    String consumerClass;

    @NotNull
    String error;

    String stackTrace;
}
