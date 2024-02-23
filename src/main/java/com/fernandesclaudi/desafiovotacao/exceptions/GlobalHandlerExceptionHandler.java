package com.fernandesclaudi.desafiovotacao.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalHandlerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IBaseException.class)
    protected ResponseEntity<Object> handleConflict(IBaseException ex, WebRequest request) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
}
