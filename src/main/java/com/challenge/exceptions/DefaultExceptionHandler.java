package com.challenge.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Throwable ex) {
        return this.getResponseEntity(NOT_FOUND, ex);
    }

    @ExceptionHandler({StaveException.class, VoteException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(Throwable ex) {
        return this.getResponseEntity(BAD_REQUEST, ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {
        return this.getResponseEntity(INTERNAL_SERVER_ERROR, ex);
    }

    private ResponseEntity<ErrorResponse> getResponseEntity(HttpStatus status, Throwable ex) {
        return new ResponseEntity<>(ErrorResponse.builder(ex, status, ExceptionUtils.getMessage(ex)).build(), status);
    }
}
