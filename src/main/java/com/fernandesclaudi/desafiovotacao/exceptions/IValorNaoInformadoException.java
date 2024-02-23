package com.fernandesclaudi.desafiovotacao.exceptions;

import org.springframework.http.HttpStatus;

public class IValorNaoInformadoException extends IBaseException {
    public IValorNaoInformadoException(String valor, HttpStatus httpStatus) {
        super("Valor de " + valor + " não encontrado", httpStatus);
    }
}
