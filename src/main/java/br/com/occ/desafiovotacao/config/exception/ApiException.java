package br.com.occ.desafiovotacao.config.exception;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


public class ApiException extends CustomException {

    public ApiException(String message, HttpStatus status) {
        super(message, status);
    }

    public ApiException(String message, HttpStatus status, Exception e) {
        super(message, status, e);
    }
}
