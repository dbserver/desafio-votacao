package com.db.desafiovotacao.api.exception;

public class CPFInvalidException extends Exception{
    public CPFInvalidException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public CPFInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
