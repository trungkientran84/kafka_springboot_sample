package com.t.t.k.ims.validation.products;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * This interface is the annotation for product existence validation
 *
 * @author ttkien
 */
@Documented
@Constraint(validatedBy = ProductValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProduct {
    String message() default "The product is not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
