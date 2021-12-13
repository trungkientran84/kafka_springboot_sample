package com.t.t.k.ims.model.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * This class stores information about a specific item in an order.
 *
 * @author ttkien
 */
@NoArgsConstructor
@Getter
public class Item {

    @PersistenceConstructor
    protected Item(String productId, String productName, Integer quantity, BigDecimal price, BigDecimal amount) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public Item(String productId, String productName, Integer quantity, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.amount = price.multiply(BigDecimal.valueOf(quantity));
    }

    @NotNull
    private String productId;

    private String productName;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    /**
     * amount = price * quantity
     */
    private BigDecimal amount; // price * quantity

    /**
     * Increase the quantity of this item
     *
     * @param quantity
     */
    public void increaseQuantity(Integer quantity) {
        this.quantity += quantity;
        this.amount = price.multiply(BigDecimal.valueOf(this.quantity));
    }

}
