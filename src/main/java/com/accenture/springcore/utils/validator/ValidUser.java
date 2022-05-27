package com.accenture.springcore.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

@Constraint(validatedBy = UserValidator.class)
@Target({TYPE, PARAMETER, METHOD, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidUser {

    String message() default "Invalid user fields.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
