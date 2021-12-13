package com.t.t.k.ims.model.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.t.t.k.ims.model.common.BasedModel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * This model stores all information that is required to generate sale report.
 *
 * @author ttkien
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
@Document(collection = "Sales")
public class Sales extends BasedModel {
    @PersistenceConstructor
    public Sales(String id, String productId, String name, String date, int hour, BigDecimal amount, int quality) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.date = date;
        this.hour = hour;
        this.amount = amount;
        this.quality = quality;
    }

    @JsonIgnore
    @Id
    private String id;

    @NotNull
    private String productId;

    @NotNull
    private String name;

    @NotNull
    private String date;

    @NotNull
    @Min(value = 0)
    @Max(value = 23)
    private int hour;

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal amount;

    @NotNull
    @Min(value = 0)
    private int quality;

    @Transient
    private int rank;

    /**
     * Accumulate the sales amount
     *
     * @param b the amount to add
     */
    public void increaseAmount(BigDecimal b) {
        this.amount = this.amount.add(b);
    }

    /**
     * Accumulate the total quantity
     *
     * @param i the quantity to add
     */
    public void increaseQuantity(int i) {
        this.quality += i;
    }

    @JsonIgnore
    public LocalDateTime getUpdatedAt() {
        return super.getUpdatedAt();
    }

    @JsonIgnore
    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

}
