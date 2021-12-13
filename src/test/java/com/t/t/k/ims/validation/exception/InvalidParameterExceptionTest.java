package com.t.t.k.ims.validation.exception;

import static org.junit.jupiter.api.Assertions.assertSame;

import com.t.t.k.ims.validation.error.Violation;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class InvalidParameterExceptionTest {
    @Test
    void testConstructor() {
        // Arrange and Act
        InvalidParameterException actualInvalidParameterException = new InvalidParameterException("Msg");
        ArrayList<Violation> violationList = new ArrayList<Violation>();
        actualInvalidParameterException.setViolations(violationList);

        // Assert
        assertSame(violationList, actualInvalidParameterException.getViolations());
    }
}

