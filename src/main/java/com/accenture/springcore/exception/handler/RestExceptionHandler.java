package com.accenture.springcore.exception.handler;

import com.accenture.springcore.exception.BadRequestException;
import com.accenture.springcore.exception.EntityNotFoundException;
import com.accenture.springcore.exception.NotAuthenticatedException;
import com.accenture.springcore.exception.NotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleException(BadRequestException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getName());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(EntityNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getName());
    }

    @ExceptionHandler({NotAuthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleException(NotAuthorizedException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getName());
    }

    @ExceptionHandler({NotAuthenticatedException.class})
    @ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
    public ExceptionResponse handleException(NotAuthenticatedException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getName());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponse handleException(ConstraintViolationException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getName());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleException(MethodArgumentNotValidException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }
}
