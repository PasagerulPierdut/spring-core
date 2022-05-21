package com.accenture.springcore.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

@Constraint(validatedBy = TransactionValidator.class)
@Target({TYPE, PARAMETER, METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidTransaction {

    String message() default "Invalid transaction fields.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
