package com.t.t.k.ims.validation.stores;

import com.t.t.k.ims.repository.stores.StoreRepository;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class validates the existence of a store
 *
 * @author ttkien
 */
public class StoreValidator implements ConstraintValidator<ValidStore, String> {

    StoreRepository storeRepository;

    public StoreValidator(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    /**
     * Checks if  there is a store with given id
     *
     * @param id      the store id
     * @param context the ConstraintValidatorContext
     * @return true if there is a store with given id otherwise false
     */
    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (id == null) return true;

        return storeRepository.existsById(id);
    }

    @Override
    public void initialize(ValidStore constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
