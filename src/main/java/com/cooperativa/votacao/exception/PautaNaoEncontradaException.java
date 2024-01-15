package com.cooperativa.votacao.exception;

public class PautaNaoEncontradaException  extends RuntimeException{
	
    private static final long serialVersionUID = 1L;
	
	public PautaNaoEncontradaException (String mensagem){
		super(mensagem);
	}


}
