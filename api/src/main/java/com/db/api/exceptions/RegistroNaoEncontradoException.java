package com.db.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException {
    public RegistroNaoEncontradoException() {
        super("O registro requerido não foi encontrado!");
    }
}
