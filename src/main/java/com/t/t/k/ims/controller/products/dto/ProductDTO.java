package com.t.t.k.ims.controller.products.dto;

import com.t.t.k.ims.validation.products.IsValidUPC;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * DTO object to hold the data related to product from the request body
 *
 * @author ttkien
 */
@Data
public class ProductDTO {
    @NotBlank
    @Size(min = 1, max = 255, message = "Name can't be blank or longer than 255 characters")
    private String name;

    @NotBlank
    @IsValidUPC
    private String upc;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal price;

    @NotNull
    @Min(value = 1)
    private Integer inventoryThreshold;
}
