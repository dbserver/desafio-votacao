package com.cooperativa.votacao.dto;

import java.time.LocalDateTime;

public class ErroDTO {
	
	private int codeStatus;
	private String tituloStatus;
	private LocalDateTime dataHora;
	private String descricaoErro;
	
	public ErroDTO(int codeStatus, String tituloStatus, String descricaoErro) {
		super();
		this.codeStatus = codeStatus;
		this.tituloStatus = tituloStatus;
		this.descricaoErro = descricaoErro;
		this.dataHora=LocalDateTime.now();
	}

	public int getCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}

	public String getTituloStatus() {
		return tituloStatus;
	}

	public void setTituloStatus(String tituloStatus) {
		this.tituloStatus = tituloStatus;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getDescricaoErro() {
		return descricaoErro;
	}

	public void setDescricaoErro(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}
	
	
	
	

}
