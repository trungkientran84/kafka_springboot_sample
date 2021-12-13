package com.t.t.k.ims.controller.products.dto;

import com.t.t.k.ims.validation.stores.ValidStore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO object to hold the data the request body related to inventory amount changing
 *
 * @author ttkien
 */
@Data
@AllArgsConstructor
public class InventoryDTO {

    @NotNull
    @ValidStore
    private String storeId;

    @Min(value = 1)
    @NotNull
    private Integer count;
}
