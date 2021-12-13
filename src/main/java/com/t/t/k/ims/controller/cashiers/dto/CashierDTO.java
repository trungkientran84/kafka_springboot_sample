package com.t.t.k.ims.controller.cashiers.dto;

import com.t.t.k.ims.validation.stores.ValidStore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO object to hold the data related to Cashier from the request
 *
 * @author ttkien
 */
@Data
public class CashierDTO {
    @NotNull
    @Size(min = 1, max = 255, message = "Name can't be blank or longer than 255 characters")
    private String name;

    @NotNull
    @ValidStore
    private String storeId;
}
