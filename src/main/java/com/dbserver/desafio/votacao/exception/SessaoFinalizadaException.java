package com.dbserver.desafio.votacao.exception;

public class SessaoFinalizadaException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Sessão já finalizada para esta Pauta!";
    }
}
