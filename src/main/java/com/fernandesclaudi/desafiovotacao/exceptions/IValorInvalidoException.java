package com.fernandesclaudi.desafiovotacao.exceptions;

import org.springframework.http.HttpStatus;

public class IValorInvalidoException extends IBaseException {
    public IValorInvalidoException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
