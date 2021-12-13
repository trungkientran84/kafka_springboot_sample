package com.t.t.k.ims.validation.cashiers;

import com.t.t.k.ims.repository.cashiers.CashierRepository;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class validates the existence of a cashier
 *
 * @author ttkien
 */
public class CashierValidator implements ConstraintValidator<ValidCashier, String> {

    CashierRepository cashierRepository;

    public CashierValidator(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    /**
     * Checks if  there is a cashier with given id
     *
     * @param id      the cashier id
     * @param context the ConstraintValidatorContext
     * @return true if there is a cashier with given id otherwise false
     */
    @Override
    public boolean isValid(String id, ConstraintValidatorContext context) {
        if (id == null) return true;

        return cashierRepository.existsById(id);
    }

    @Override
    public void initialize(ValidCashier constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
