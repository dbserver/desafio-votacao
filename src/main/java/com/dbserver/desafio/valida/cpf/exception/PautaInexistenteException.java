package com.dbserver.desafio.valida.cpf.exception;

public class PautaInexistenteException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Pauta Inexistente!";
    }
}
