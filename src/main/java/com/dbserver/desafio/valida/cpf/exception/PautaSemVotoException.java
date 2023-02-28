package com.dbserver.desafio.valida.cpf.exception;

public class PautaSemVotoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Esta Pauta não contém votos!";
    }
}
