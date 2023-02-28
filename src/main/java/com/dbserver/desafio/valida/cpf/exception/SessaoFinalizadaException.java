package com.dbserver.desafio.valida.cpf.exception;

public class SessaoFinalizadaException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Sessão já finalizada para esta Pauta!";
    }
}
