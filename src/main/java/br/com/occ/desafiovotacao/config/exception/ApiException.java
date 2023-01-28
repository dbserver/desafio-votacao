package br.com.occ.desafiovotacao.config.exception;

import org.springframework.http.HttpStatus;


public class ApiException extends CustomException {

    public ApiException(String message, HttpStatus status) {
        super(message, status);
    }

    public ApiException(String message, HttpStatus status, Exception e) {
        super(message, status, e);
    }
}
