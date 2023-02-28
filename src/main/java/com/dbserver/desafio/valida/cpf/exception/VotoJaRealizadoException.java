package com.dbserver.desafio.valida.cpf.exception;

public class VotoJaRealizadoException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Voto já foi realizado para esse CPF!";
    }
}
