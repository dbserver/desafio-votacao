package com.cooperativa.votacao.exception;

public class VotoInvalidoException extends RuntimeException{
	
	 private static final long serialVersionUID = 1L;
		
		public VotoInvalidoException (String mensagem){
			super(mensagem);
		}


}
