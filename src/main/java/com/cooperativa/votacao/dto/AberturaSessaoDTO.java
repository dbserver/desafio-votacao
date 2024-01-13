package com.cooperativa.votacao.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class AberturaSessaoDTO {
	
	@NotNull
	private  Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime tempoSessao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getTempoSessao() {
		return tempoSessao;
	}

	public void setTempoSessao(LocalDateTime tempoSessao) {
		if(tempoSessao!=null)
			this.tempoSessao=tempoSessao;
		else
			this.tempoSessao=LocalDateTime.now().plusMinutes(1l);
	}
	
	

}
