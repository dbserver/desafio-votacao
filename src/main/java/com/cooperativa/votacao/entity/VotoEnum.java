package com.cooperativa.votacao.entity;

public enum VotoEnum {
	
	SIM("Sim"),
	NAO("Não");
	
	private String voto;
	
	private VotoEnum(String voto) {
		this.voto = voto;
	}

	public String getVoto() {
		return voto;
	}


	
}
