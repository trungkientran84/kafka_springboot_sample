package com.t.t.k.ims.validation.products;

import com.t.t.k.ims.repository.products.ProductRepository;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class validates the existence of a product
 *
 * @author ttkien
 */
public class ProductValidator implements ConstraintValidator<ValidProduct, String> {


    ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Checks if  there is a product with given id
     *
     * @param id      the product id
     * @param context the ConstraintValidatorContext
     * @return true if there is a product with given id otherwise false
     */
    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (id == null) return true;

        return productRepository.existsById(id);
    }

    @Override
    public void initialize(ValidProduct constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
