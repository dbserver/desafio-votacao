package com.fernandesclaudi.desafiovotacao.exceptions;

import org.springframework.http.HttpStatus;

public class IRegistroNaoEncontradoException extends IBaseException {

    public IRegistroNaoEncontradoException(String entidade) {
        super("Nenhum registro para " + entidade + " foi encontrado", HttpStatus.NOT_FOUND);
    }
}
