package com.t.t.k.ims.model.orders;

import com.t.t.k.ims.common.enums.OrderStatus;
import com.t.t.k.ims.model.common.BasedModel;
import com.t.t.k.ims.validation.cashiers.ValidCashier;
import com.t.t.k.ims.validation.stores.ValidStore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This model store all information related to an Order.
 *
 * @author ttkien
 */

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Document(collection = "Orders")
public class Order extends BasedModel {

    @Id
    private String id;

    @NotNull
    @ValidStore
    private String storeId;

    @NotNull
    @ValidCashier
    private String cashierId;

    @NotNull
    @Valid
    private Map<String, Item> items = new HashMap<>();

    @NotNull
    @Valid
    private Map<String, Payment> payments = new HashMap<>();

    @NotNull
    private List<String> promotions = new ArrayList<>();

    /**
     * Sum of all order's items' amount
     */
    @NotNull
    private BigDecimal subAmount = new BigDecimal(0);

    @NotNull
    private BigDecimal discountAmount = new BigDecimal(0);
    ;

    @NotNull
    private BigDecimal taxPercent = new BigDecimal(0);
    ;

    /**
     * TaxAmount = subAmount * taxPercent
     */
    @NotNull
    private BigDecimal taxAmount = new BigDecimal(0);
    ;

    /**
     * Total amount to be paid = (subAmount - discountAmount) + taxAmount
     */
    @NotNull
    private BigDecimal totalAmount = new BigDecimal(0);
    ;     // Total amount to be paid = (subAmount - discountAmount) + taxAmount.


    @NotNull
    private OrderStatus status = OrderStatus.OPEN;
}
