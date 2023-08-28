package com.db.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PautaException extends RuntimeException{
    public PautaException(String message) {
        super(message);
    }
}
