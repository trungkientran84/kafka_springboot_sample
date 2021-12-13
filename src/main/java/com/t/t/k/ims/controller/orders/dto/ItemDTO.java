package com.t.t.k.ims.controller.orders.dto;

import com.t.t.k.ims.validation.products.IsValidUPC;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * DTO object contains the required data to add a product to a new order
 *
 * @author ttkien
 */
@Data
public class ItemDTO {

    @NotNull
    @IsValidUPC
    private String upc;

    @NotNull
    @Min(value = 1)
    private Integer quantity;
}
