package com.accenture.springcore.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = TransactionValidator.class)
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidTransaction {

    String message() default "Invalid transaction fields.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
