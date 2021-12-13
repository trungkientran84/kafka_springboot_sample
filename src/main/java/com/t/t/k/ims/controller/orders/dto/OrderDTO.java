package com.t.t.k.ims.controller.orders.dto;

import com.t.t.k.ims.validation.cashiers.ValidCashier;
import com.t.t.k.ims.validation.stores.ValidStore;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * DTO object contains the required data to create a new order
 *
 * @author ttkien
 */
@Data
public class OrderDTO {
    @NotNull
    @ValidCashier
    private String cashierId;

    @NotNull
    @ValidStore
    private String storeId;
}
