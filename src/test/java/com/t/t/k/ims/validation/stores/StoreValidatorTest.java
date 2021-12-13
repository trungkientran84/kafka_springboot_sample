package com.t.t.k.ims.validation.stores;

import com.t.t.k.ims.repository.stores.StoreRepository;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

import javax.validation.ClockProvider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class StoreValidatorTest {

    @Test
    void testIsValid() {
        // Arrange
        StoreRepository storeRepository = mock(StoreRepository.class);
        when(storeRepository.existsById((String) any())).thenReturn(true);
        StoreValidator storeValidator = new StoreValidator(storeRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertTrue(storeValidator.isValid("42",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
        verify(storeRepository).existsById((String) any());
    }

    @Test
    void testIsValid2() {
        // Arrange
        StoreRepository storeRepository = mock(StoreRepository.class);
        when(storeRepository.existsById((String) any())).thenReturn(false);
        StoreValidator storeValidator = new StoreValidator(storeRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertFalse(storeValidator.isValid("42",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
        verify(storeRepository).existsById((String) any());
    }

    @Test
    void testIsValid3() {
        // Arrange
        StoreRepository storeRepository = mock(StoreRepository.class);
        when(storeRepository.existsById((String) any())).thenReturn(true);
        StoreValidator storeValidator = new StoreValidator(storeRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertTrue(storeValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }
}

