package com.db.desafiovotacao.api.exception;

public class AgendaNotFoundException extends Exception{
    public AgendaNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public AgendaNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
