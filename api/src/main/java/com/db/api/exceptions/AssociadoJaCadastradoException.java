package com.db.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AssociadoJaCadastradoException extends RuntimeException {
    public AssociadoJaCadastradoException() {
        super("O associado com esse cpf já está registrado no sistema!");
    }
}
