package com.db.desafiovotacao.api.exception;

public class MemberNotFoundException extends Exception{
    public MemberNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public MemberNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
