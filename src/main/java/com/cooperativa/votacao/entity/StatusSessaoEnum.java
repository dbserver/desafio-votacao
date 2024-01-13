package com.cooperativa.votacao.entity;

public enum StatusSessaoEnum {
	
	CRIADO (1),
	ABERTO (2),
	FINALIZADO(3);
	
	private Integer id;
	
		
	private StatusSessaoEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	
	
	
}
