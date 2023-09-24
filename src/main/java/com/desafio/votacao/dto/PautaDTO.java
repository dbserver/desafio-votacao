package com.desafio.votacao.dto;

import java.time.LocalDateTime;

import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.enums.PautaStatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PautaDTO {
	
	private Long id; 
	private String descricao; 
	private boolean ativo = true; 
	private LocalDateTime dthCriacao = LocalDateTime.now();
	private Long qtdVotosNao = 0L;
	private Long qtdVotosSim = 0L;
	
	@Enumerated(EnumType.STRING)
	private PautaStatusEnum status;

	public PautaDTO(Pauta entity) {
		this.id = entity.getId();
		this.descricao = entity.getDescricao();
		this.qtdVotosNao = entity.getQtdVotosNao();
		this.qtdVotosSim = entity.getQtdVotosSim();
		this.ativo = entity.isAtivo();
		this.dthCriacao = entity.getDthCriacao();	
		this.status = entity.getStatus();
	}
	
}

