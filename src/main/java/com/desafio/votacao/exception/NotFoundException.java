package com.desafio.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException { 
	
	private static final long serialVersionUID = -2334562826308090349L;

	public NotFoundException(String mensagem) {
        super(mensagem);
    }
}