package com.accenture.springcore.exception.customExceptions;

public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
    }
}
