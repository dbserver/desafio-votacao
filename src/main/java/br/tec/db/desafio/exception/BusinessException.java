package br.tec.db.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends RuntimeException {

    public BusinessException(String message, Exception e) {
        super(message,e);
    }

    public BusinessException(String message) {
        super (message);
    }
}

