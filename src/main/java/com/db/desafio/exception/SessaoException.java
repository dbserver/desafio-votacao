package com.db.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SessaoException extends RuntimeException{
    public SessaoException(String message) {
        super(message);
    }
}
