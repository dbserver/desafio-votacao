package com.desafio.projeto_votacao.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final int errorCode;
    private final HttpStatus status;
    private final String message;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.errorCode = status.value();
        this.status = status;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static HttpStatus getHttpStatus(int errorCode) {
        switch (errorCode) {
            case 400:
                return HttpStatus.BAD_REQUEST;
            case 402:
                return HttpStatus.PAYMENT_REQUIRED;
            case 403:
                return HttpStatus.FORBIDDEN;
            case 404:
                return HttpStatus.NOT_FOUND;
            case 405:
                return HttpStatus.METHOD_NOT_ALLOWED;
            case 409:
                return HttpStatus.CONFLICT;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}