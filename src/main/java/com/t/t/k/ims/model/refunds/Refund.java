package com.t.t.k.ims.model.refunds;

import com.t.t.k.ims.common.enums.RefundStatus;
import com.t.t.k.ims.model.common.BasedModel;
import com.t.t.k.ims.model.orders.Payment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * This class stores information about a specific refund.
 *
 * @author ttkien
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Refunds")
public class Refund extends BasedModel {

    @Id
    private String id;

    @NotNull
    private String orderId;

    @NotNull
    private Payment payment;

    @NotNull
    private RefundStatus status;

    public Refund(String orderId, Payment payment, RefundStatus status) {
        this.orderId = orderId;
        this.payment = payment;
        this.status = status;
    }
}
