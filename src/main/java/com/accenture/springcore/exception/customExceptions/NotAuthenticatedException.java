package com.accenture.springcore.exception.customExceptions;

public class NotAuthenticatedException extends RuntimeException {

    public NotAuthenticatedException(String message, Throwable error) {
        super(message, error);
    }
}
