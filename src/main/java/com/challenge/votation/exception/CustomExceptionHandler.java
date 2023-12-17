package com.challenge.votation.exception;

import com.challenge.votation.model.Error;
import com.challenge.votation.model.ErrorObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Log4j2
public class CustomExceptionHandler {
    @ExceptionHandler(VotationException.class)
    public ResponseEntity<Object> handleVotationException(VotationException ex) {
        log.warn("Handler Votation Exception: {}", ex.getMessage());

        Error error = new Error(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(VotationNotFoundException.class)
    public ResponseEntity<Object> handleVotationNotFoundException(VotationNotFoundException ex) {
        log.warn("Handler Votation Not Found Exception: {}", ex.getMessage());

        Error error = new Error(ex.getMessage(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), null, null);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("Handler Method Argument Not Valid Exception: {}", ex.getMessage());

        List<ErrorObject> errors = getErrors(ex);
        Error error = getError(ex, errors);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private Error getError(MethodArgumentNotValidException ex, List<ErrorObject> errors) {
        return new Error("Invalid Fields", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }

    private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .toList();
    }
}
