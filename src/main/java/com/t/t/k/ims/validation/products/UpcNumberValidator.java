package com.t.t.k.ims.validation.products;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class validates the upc number
 *
 * @author ttkien
 */
public class UpcNumberValidator implements ConstraintValidator<IsValidUPC, String> {

    /**
     * Checks if given string is valid as and upc number
     *
     * @param input the upc string to validate
     * @param constraintValidatorContext
     * @return true if valid upc otherwise false
     */
    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {

        if (input == null || input.length() == 0) return true;

        if (input.length() != 13) return false;

        try {
            Long.parseLong(input);
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }
}
