package com.fernandesclaudi.desafiovotacao.exceptions;

import org.springframework.http.HttpStatus;

public class IRegistroJaInseridoException extends IBaseException {

    public IRegistroJaInseridoException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
