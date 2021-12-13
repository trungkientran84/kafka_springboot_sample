package com.t.t.k.ims.model.cashiers;

import com.t.t.k.ims.model.common.BasedModel;
import com.t.t.k.ims.validation.stores.ValidStore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This model stores all information of a Cashier.
 *
 * @author ttkien
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Cashiers")
public class Cashier extends BasedModel {

    @Id
    private String id;

    @NotNull
    @Size(min = 1, max = 255, message = "Name can't be blank or longer than 255 characters")
    private String name;

    @NotNull
    @ValidStore
    private String storeId;
}
