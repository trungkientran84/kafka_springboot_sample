package com.t.t.k.ims.validation.products;

import com.t.t.k.ims.repository.products.ProductRepository;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.hibernate.validator.messageinterpolation.ExpressionLanguageFeatureLevel;
import org.junit.jupiter.api.Test;

import javax.validation.ClockProvider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ProductValidatorTest {


    @Test
    void testIsValid() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.existsById((String) any())).thenReturn(true);
        ProductValidator productValidator = new ProductValidator(productRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertTrue(productValidator.isValid("42",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
        verify(productRepository).existsById((String) any());
    }

    @Test
    void testIsValid2() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.existsById((String) any())).thenReturn(false);
        ProductValidator productValidator = new ProductValidator(productRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertFalse(productValidator.isValid("42",
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
        verify(productRepository).existsById((String) any());
    }

    @Test
    void testIsValid3() {
        // Arrange
        ProductRepository productRepository = mock(ProductRepository.class);
        when(productRepository.existsById((String) any())).thenReturn(true);
        ProductValidator productValidator = new ProductValidator(productRepository);
        ClockProvider clockProvider = mock(ClockProvider.class);

        // Act and Assert
        assertTrue(productValidator.isValid(null,
                new ConstraintValidatorContextImpl(clockProvider, PathImpl.createRootPath(), null,
                        "Constraint Validator Payload", ExpressionLanguageFeatureLevel.DEFAULT,
                        ExpressionLanguageFeatureLevel.DEFAULT)));
    }


}

