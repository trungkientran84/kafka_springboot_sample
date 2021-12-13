package com.t.t.k.ims.validation.products;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * This class is the annotation to validate product upc number
 *
 * @author ttkien
 */
@Documented
@Constraint(validatedBy = UpcNumberValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidUPC {
    String message() default "Invalid UPC number. A UPC number must contain 13 digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
