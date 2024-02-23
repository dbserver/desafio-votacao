package com.fernandesclaudi.desafiovotacao.exceptions;

import org.springframework.http.HttpStatus;

public class IValorNaoInformadoException extends IBaseException {
    public IValorNaoInformadoException(String valor) {
        super("Valor de " + valor + " não encontrado", HttpStatus.BAD_REQUEST);
    }
}
