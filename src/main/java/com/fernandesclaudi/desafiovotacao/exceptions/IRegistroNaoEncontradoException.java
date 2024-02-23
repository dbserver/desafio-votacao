package com.fernandesclaudi.desafiovotacao.exceptions;

import org.springframework.http.HttpStatus;

public class IRegistroNaoEncontradoException extends IBaseException {

    public IRegistroNaoEncontradoException(String entidade, HttpStatus httpStatus) {
        super("Nenhum registro para " + entidade + " foi encontrado", httpStatus);
    }
}
