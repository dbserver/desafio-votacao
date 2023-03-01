package com.dbserver.desafio.votacao.exception;

public class PautaInexistenteException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Pauta Inexistente!";
    }
}
