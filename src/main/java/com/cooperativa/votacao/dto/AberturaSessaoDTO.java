package com.cooperativa.votacao.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class AberturaSessaoDTO {
	
	@NotNull
	private  Integer idPauta;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime tempoSessao;


	public Integer getIdPauta() {
		return idPauta;
	}

	public void setIdPauta(Integer idPauta) {
		this.idPauta = idPauta;
	}

	public LocalDateTime getTempoSessao() {
		return tempoSessao;
	}

	public void setTempoSessao(LocalDateTime tempoSessao) {
		this.tempoSessao=tempoSessao;
	}
	
	

}
