package com.t.t.k.ims.controller.orders.dto;

import com.t.t.k.ims.common.enums.PaymentMethod;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * DTO object contains the required data to add a product to a new order
 *
 * @author ttkien
 */
@Data
public class PaymentDTO {

    @NotNull
    private PaymentMethod paymentMethod;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;
}
