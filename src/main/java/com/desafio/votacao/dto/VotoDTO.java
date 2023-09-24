package com.desafio.votacao.dto;

import java.time.LocalDateTime;

import com.desafio.votacao.entity.Voto;

import lombok.Data;

@Data
public class VotoDTO {
	private Long id;

	private LocalDateTime dthCriacao;

	private boolean voto;

	private PautaDTO pauta;

	private AssociadoDTO associado;
	
	public VotoDTO(Voto entity) {
		this.id = entity.getId(); 
		this.dthCriacao = entity.getDthCriacao();		
		this.voto = entity.isVoto();
		this.pauta = new PautaDTO(entity.getPauta());
		this.associado = new AssociadoDTO(entity.getAssociado());
	}
}
