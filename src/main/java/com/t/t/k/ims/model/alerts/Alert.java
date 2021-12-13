package com.t.t.k.ims.model.alerts;

import com.t.t.k.ims.common.enums.AlertType;
import com.t.t.k.ims.model.common.BasedModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This model stores all information of a Cashier.
 *
 * @author ttkien
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Alerts")
public class Alert extends BasedModel {

    public Alert(AlertType type, String messages) {
        this.type = type;
        this.messages = messages;
    }

    @Id
    private String id;

    @NotNull
    AlertType type;

    @NotNull
    @Size(min = 1)
    private String messages;
}
