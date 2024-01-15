package com.cooperativa.votacao.dto;

import jakarta.validation.constraints.NotBlank;

public class PautaDTO {

	private  Integer id;
	@NotBlank
	private String nome;
	
	private String statusSessao;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatusSessao() {
		return statusSessao;
	}

	public void setStatusSessao(String statusSessao) {
		this.statusSessao = statusSessao;
	}


	
	
	
	
	
	
}
