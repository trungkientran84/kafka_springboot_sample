package com.t.t.k.ims.model.products;

import com.t.t.k.ims.model.common.BasedModel;
import com.t.t.k.ims.validation.products.ValidProduct;
import com.t.t.k.ims.validation.stores.ValidStore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * This model store all information about product inventory.
 *
 * @author ttkien
 */

@Data
@Document(collection = "Inventories")
public class Inventory extends BasedModel {

    @Id
    private String id;

    @NotNull
    @ValidProduct
    @Indexed
    private String productId;

    @NotNull
    @ValidStore
    @Indexed
    private String storeId;

    @NotNull
    @Min(value = 0)
    private Integer count;

    public void decrease(Integer amount) {
        count-=amount;
    }

    public void increase(Integer amount) {
        count+=amount;
    }

}
