package com.cooperativa.votacao.exception;

public class SessaoException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SessaoException (String mensagem){
		super(mensagem);
	}

}
