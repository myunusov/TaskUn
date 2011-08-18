package org.maxur.commons.annotation;


import org.maxur.commons.validators.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @author Maxim Yunusov
 * @version 1.0 7/26/11
 */
@Documented
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {UniqueValidator.class})
public @interface Unique {
    String message() default "{org.hibernate.validator.constraints.Unique.message}";

    String[] properties();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}