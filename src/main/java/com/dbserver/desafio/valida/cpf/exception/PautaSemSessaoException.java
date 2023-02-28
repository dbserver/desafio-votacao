package com.dbserver.desafio.valida.cpf.exception;

public class PautaSemSessaoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Não existe sessão para esta Pauta";
    }
}
