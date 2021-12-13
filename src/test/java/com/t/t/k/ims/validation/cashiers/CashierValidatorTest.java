package com.t.t.k.ims.validation.cashiers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.t.t.k.ims.repository.cashiers.CashierRepository;

import javax.validation.ClockProvider;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

class CashierValidatorTest {


    @Test
    void testIsValid() {
        // Arrange
        CashierRepository cashierRepository = mock(CashierRepository.class);
        when(cashierRepository.existsById((String) any())).thenReturn(true);
        CashierValidator cashierValidator = new CashierValidator(cashierRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertTrue(cashierValidator.isValid("42",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
        verify(cashierRepository).existsById((String) any());
    }

    @Test
    void testIsValid2() {
        // Arrange
        CashierRepository cashierRepository = mock(CashierRepository.class);
        when(cashierRepository.existsById((String) any())).thenReturn(false);
        CashierValidator cashierValidator = new CashierValidator(cashierRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertFalse(cashierValidator.isValid("42",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
        verify(cashierRepository).existsById((String) any());
    }

    @Test
    void testIsValid3() {
        // Arrange
        CashierRepository cashierRepository = mock(CashierRepository.class);
        when(cashierRepository.existsById((String) any())).thenReturn(true);
        CashierValidator cashierValidator = new CashierValidator(cashierRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertTrue(cashierValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }

}

