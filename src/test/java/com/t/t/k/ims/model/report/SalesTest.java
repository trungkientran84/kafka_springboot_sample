package com.t.t.k.ims.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class SalesTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        Sales actualSales = new Sales("42", "42", "Name", "2020-03-01", 1, BigDecimal.valueOf(1L), 1);
        actualSales.increaseQuantity(1);

        // Assert
        assertEquals(2, actualSales.getQuality());
    }

    @Test
    void testIncreaseAmount() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by increaseAmount(BigDecimal)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        // Arrange
        Sales sales = new Sales("42", "42", "Name", "2020-03-01", 1, BigDecimal.valueOf(1L), 1);

        // Act
        sales.increaseAmount(BigDecimal.valueOf(1L));
    }

    @Test
    void testIncreaseAmount2() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by increaseAmount(BigDecimal)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        // Arrange
        Sales sales = new Sales("42", "42", "Name", "2020-03-01", 1, BigDecimal.valueOf(1L), 1, 1);

        // Act
        sales.increaseAmount(BigDecimal.valueOf(1L));
    }

    @Test
    void testGetUpdatedAt() {
        // Arrange, Act and Assert
        assertNull((new Sales("42", "42", "Name", "2020-03-01", 1, BigDecimal.valueOf(1L), 1)).getUpdatedAt());
    }

    @Test
    void testGetCreatedAt() {
        // Arrange, Act and Assert
        assertNull((new Sales("42", "42", "Name", "2020-03-01", 1, BigDecimal.valueOf(1L), 1)).getCreatedAt());
    }
}

