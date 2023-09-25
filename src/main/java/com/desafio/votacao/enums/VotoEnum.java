package com.desafio.votacao.enums;

public enum VotoEnum {
	SIM(true),NAO(false);
	
	private final boolean valor;

	VotoEnum(boolean voto) {
		this.valor = voto;
	}
	
	public boolean getVoto() {
		return valor;
	}
}
