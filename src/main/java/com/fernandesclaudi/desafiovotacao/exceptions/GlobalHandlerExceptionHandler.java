package com.fernandesclaudi.desafiovotacao.exceptions;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalHandlerExceptionHandler extends ResponseEntityExceptionHandler {

    @Override protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status,
            @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        return ResponseEntity.status(status).body(ex.getAllValidationResults().stream().map(validationResult -> {
            String errorMessage = validationResult.getResolvableErrors().stream().findFirst().get().getDefaultMessage();
            assert errorMessage != null;
            return errorMessage;
        }));
    }

    @ExceptionHandler(IBaseException.class)
    public ResponseEntity<Object> handleConflict(IBaseException ex) {
       return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }


}
