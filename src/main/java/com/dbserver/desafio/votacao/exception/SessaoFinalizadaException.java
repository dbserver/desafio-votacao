package com.dbserver.desafio.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class SessaoFinalizadaException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Sessão já finalizada para esta Pauta!";
    }
}
