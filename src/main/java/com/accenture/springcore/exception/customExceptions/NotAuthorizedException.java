package com.accenture.springcore.exception.customExceptions;

public class NotAuthorizedException extends RuntimeException{

    public NotAuthorizedException(String message, Throwable error) {
        super(message, error);
    }
}
