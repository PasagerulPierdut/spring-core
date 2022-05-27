package com.accenture.springcore.utils.validator;

import com.accenture.springcore.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<ValidUser, User> {

    private final String usernameRegex = "[^A-Za-z0-9]+";
    private final String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        boolean validName = user.getUsername() != null;
        if(!validName){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Username is required. Only letters and numbers.");
        }
        boolean validUsername = user.getUsername().matches(usernameRegex);
        if(!validUsername) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Only letters and numbers.");
        }
        boolean validPassword = user.getPassword().matches(passRegex);
        if(!validPassword) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Password must contain 8-20 chars, 1 lowercase, 1 UPPERCASE, 1 digit.");
        }
        return validName && validUsername && validPassword;
    }

    @Override
    public void initialize(ValidUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
