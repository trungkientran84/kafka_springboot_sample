package com.t.t.k.ims.validation.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import javax.validation.ClockProvider;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class UpcNumberValidatorTest {
    @Test
    void testIsValid() {
        // Arrange
        UpcNumberValidator upcNumberValidator = new UpcNumberValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertFalse(upcNumberValidator.isValid("Input",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    @Test
    void testIsValid2() {
        // Arrange
        UpcNumberValidator upcNumberValidator = new UpcNumberValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertTrue(upcNumberValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }

    @Test
    void testIsValid3() {
        // Arrange
        UpcNumberValidator upcNumberValidator = new UpcNumberValidator();
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertTrue(upcNumberValidator.isValid("",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

