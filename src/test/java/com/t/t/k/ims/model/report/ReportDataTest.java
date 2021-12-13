package com.t.t.k.ims.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ReportDataTest {
    @Test
    void testConstructor() {
        // Arrange
        BigDecimal valueOfResult = BigDecimal.valueOf(1L);

        // Act
        ReportData actualReportData = new ReportData("2020-03-01", "42", "Name", valueOfResult, 1);

        // Assert
        assertNull(actualReportData.getAccumulateAmount());
        assertEquals("ReportData(date=2020-03-01, productId=42, name=Name, hour=null, amount=1, accumulateAmount=null,"
                + " quality=1, rank=0)", actualReportData.toString());
        assertEquals(0, actualReportData.getRank());
        assertEquals(1, actualReportData.getQuality());
        assertEquals("42", actualReportData.getProductId());
        assertEquals("Name", actualReportData.getName());
        assertNull(actualReportData.getHour());
        assertEquals("2020-03-01", actualReportData.getDate());
        BigDecimal expectedAmount = valueOfResult.ONE;
        BigDecimal amount = actualReportData.getAmount();
        assertSame(expectedAmount, amount);
        assertEquals(0, amount.scale());
        assertEquals("1", amount.toString());
        assertEquals(1, amount.signum());
        assertSame(amount, valueOfResult);
    }

    @Test
    void testConstructor2() {
        // Arrange and Act
        ReportData actualReportData = new ReportData("2020-03-01", "42", "Name", 1, BigDecimal.valueOf(1L), 1);

        // Assert
        assertEquals(
                "ReportData(date=2020-03-01, productId=42, name=Name, hour=1, amount=1, accumulateAmount=null, quality=1,"
                        + " rank=0)",
                actualReportData.toString());
        assertEquals(0, actualReportData.getRank());
        assertEquals(1, actualReportData.getQuality());
        assertEquals("42", actualReportData.getProductId());
        assertEquals("Name", actualReportData.getName());
        assertEquals(1, actualReportData.getHour().intValue());
        assertEquals("2020-03-01", actualReportData.getDate());
        assertEquals("1", actualReportData.getAmount().toString());
    }

    @Test
    void testCalcAmount() {
        // Arrange
        ReportData reportData = new ReportData();
        reportData.setAccumulateAmount(new ArrayList<String>());

        // Act
        reportData.calcAmount();

        // Assert
        assertEquals("ReportData(date=null, productId=null, name=null, hour=null, amount=0, accumulateAmount=[], quality=0,"
                + " rank=0)", reportData.toString());
    }
}

