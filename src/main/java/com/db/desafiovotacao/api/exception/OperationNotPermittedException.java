package com.db.desafiovotacao.api.exception;

public class OperationNotPermittedException extends Exception{
    public OperationNotPermittedException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public OperationNotPermittedException(String errorMessage) {
        super(errorMessage);
    }
}
