package com.desafio.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessException extends RuntimeException { 
	
	private static final long serialVersionUID = -2334562826308090349L;

	public BusinessException(String mensagem) {
        super(mensagem);
    }
}