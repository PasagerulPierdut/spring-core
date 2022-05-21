package com.accenture.springcore.utils.validator;

import com.accenture.springcore.model.Transaction;
import com.accenture.springcore.model.TransactionType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class TransactionValidator implements ConstraintValidator<ValidTransaction, Transaction> {

    @Override
    public void initialize(ValidTransaction constraintAnnotation) {
    }

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext constraintValidatorContext) {
        boolean validUserId = transaction.getUserId() != null;
        if (!validUserId) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Every transaction should belong to some user.");
        }
        boolean validProduct = transaction.getProduct() != null;
        if (!validProduct) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "The product must have a name.").addConstraintViolation();
        }
        boolean validType = transaction.getTransactionType() == TransactionType.BUY || transaction.getTransactionType() == TransactionType.SELL;
        if (!validType) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Only BUY or SELL types allowed.").addConstraintViolation();
        }
        boolean validAmount = transaction.getAmount() > 0.0;
        if (!validAmount) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Amount must be a positive value.").addConstraintViolation();
        }
        boolean validCreation = transaction.getCreatedAt().isBefore(LocalDateTime.now()) &&
                (transaction.getCreatedAt() != null);
        if (validCreation) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "Null or future dates are, obviously, not allowed.");
        }
        return validType && validAmount && validProduct && validCreation;
    }
}
