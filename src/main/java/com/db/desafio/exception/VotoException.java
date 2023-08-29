package com.db.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VotoException extends RuntimeException{
    public VotoException(String message) {
        super(message);
    }
}
