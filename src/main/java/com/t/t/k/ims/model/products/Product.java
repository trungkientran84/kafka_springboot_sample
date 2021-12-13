package com.t.t.k.ims.model.products;

import com.t.t.k.ims.model.common.BasedModel;
import com.t.t.k.ims.validation.products.IsValidUPC;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * This model store all information about a product.
 *
 * @author ttkien
 */

@Data
@Document(collection = "Products")
public class Product extends BasedModel {

    @Id
    private String id;

    @NotBlank
    @Size(min = 1, max = 255, message = "Name can't be blank or longer than 255 characters")
    private String name;

    @NotBlank
    @IsValidUPC
    @Indexed(unique = true)
    private String upc;

    @Min(value = 0)
    @NotNull
    private BigDecimal price;

    @Min(value = 0)
    @NotNull
    private Integer inventoryThreshold;

}
