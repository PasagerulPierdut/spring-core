package com.accenture.springcore.exception;

public class NotAuthorizedException extends Exception{

    public NotAuthorizedException(String message, Throwable error) {
        super(message, error);
    }
}
