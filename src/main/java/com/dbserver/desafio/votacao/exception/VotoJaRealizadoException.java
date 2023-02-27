package com.dbserver.desafio.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class VotoJaRealizadoException extends Exception {

    @Override
    public String getMessage() {
        return "Voto já foi realizado para esse CPF!";
    }
}
