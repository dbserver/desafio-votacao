package com.desafio.projeto_votacao.exceptions;

public class ErrorResponse {
    private final int errorCode;
    private final String message;

    public ErrorResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}