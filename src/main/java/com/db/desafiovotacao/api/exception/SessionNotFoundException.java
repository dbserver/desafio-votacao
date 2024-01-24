package com.db.desafiovotacao.api.exception;

public class SessionNotFoundException extends Exception{
    public SessionNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public SessionNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
