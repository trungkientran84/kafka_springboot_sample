package com.t.t.k.ims.model.stores;

import com.t.t.k.ims.model.common.BasedModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This model stores all information of a store.
 *
 * @author ttkien
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "Stores")
public class Store extends BasedModel {

    @Id
    private String id;

    @NotNull
    @Size(min = 1, max = 255, message = "Name can't be blank or longer than 255 characters")
    private String name;
}
