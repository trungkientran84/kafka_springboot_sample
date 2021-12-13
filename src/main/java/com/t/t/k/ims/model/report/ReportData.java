package com.t.t.k.ims.model.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * This model contains report data after aggregated
 *
 * @author ttkien
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportData {

    public ReportData(String date, String productId, String name, int hour, BigDecimal amount, int quality) {
        this.date = date;
        this.productId = productId;
        this.name = name;
        this.hour = hour;
        this.amount = amount;
        this.quality = quality;
        this.rank = rank;
    }

    public ReportData(String date, String productId, String name, BigDecimal amount, int quality) {
        this.date = date;
        this.productId = productId;
        this.name = name;
        this.amount = amount;
        this.quality = quality;
        this.rank = rank;
    }

    private String date;

    private String productId;

    private String name;

    private Integer hour = null;

    private BigDecimal amount;

    @JsonIgnore
    private ArrayList<String> accumulateAmount;

    private int quality;

    @Setter
    private int rank;

    public void calcAmount() {
        BigDecimal a = BigDecimal.ZERO;
        for (String s : accumulateAmount) {
            a = a.add(new BigDecimal(s));
        }

        this.amount = a;
    }

}
