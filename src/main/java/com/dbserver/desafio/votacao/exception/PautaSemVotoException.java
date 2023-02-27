package com.dbserver.desafio.votacao.exception;

public class PautaSemVotoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Esta Pauta não contém votos!";
    }
}
