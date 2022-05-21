package com.accenture.springcore.exception.customExceptions;

public class NotAuthenticatedException extends Exception {

    public NotAuthenticatedException(String message, Throwable error) {
        super(message, error);
    }
}
