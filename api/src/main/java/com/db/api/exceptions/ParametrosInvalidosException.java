package com.db.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ParametrosInvalidosException extends RuntimeException {
    public ParametrosInvalidosException(String message) {
        super(message);
    }
}
