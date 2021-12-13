package com.t.t.k.ims.model.orders;

import com.t.t.k.ims.common.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class stores information about a specific payment in an order.
 *
 * @author ttkien
 */
@NoArgsConstructor
@Getter
public class Payment {

    @PersistenceConstructor
    protected Payment(String id, PaymentMethod paymentMethod, BigDecimal amount) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public Payment(PaymentMethod paymentMethod, BigDecimal amount) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;

        //Generate payment id from payment method and current datetime.
        this.id = paymentMethod.name().concat("-").concat(LocalDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH-mm-ss-SSSSSSSSS")));
    }

    @NotNull
    private String id;

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

}
