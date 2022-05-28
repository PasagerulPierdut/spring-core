package com.accenture.springcore.exception.handler;

import com.accenture.springcore.exception.customExceptions.BadRequestException;
import com.accenture.springcore.exception.customExceptions.EntityNotFoundException;
import com.accenture.springcore.exception.customExceptions.NotAuthenticatedException;
import com.accenture.springcore.exception.customExceptions.NotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.jms.listener.adapter.ListenerExecutionFailedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerErrorException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleException(BadRequestException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleException(EntityNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }

    @ExceptionHandler({NotAuthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse handleException(NotAuthorizedException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }

    @ExceptionHandler({NotAuthenticatedException.class})
    @ResponseStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED)
    public ExceptionResponse handleException(NotAuthenticatedException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponse handleException(ConstraintViolationException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleException(MethodArgumentNotValidException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }

    @ExceptionHandler(ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(ServerErrorException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }

@ExceptionHandler(ListenerExecutionFailedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(ListenerExecutionFailedException exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ExceptionResponse handleException(Exception exception) {
        return new ExceptionResponse(exception.getMessage(), exception.getClass().getSimpleName());
    }
}
