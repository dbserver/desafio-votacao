package com.desafio.votacao.entity;

import java.time.LocalDateTime;

import com.desafio.votacao.dto.PautaDTO;
import com.desafio.votacao.enums.PautaStatusEnum;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pauta { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	@Column(name = "descricao", nullable = false, columnDefinition="Coluna que representa a descrição da Pauta.")
	private String descricao;
	
	@Basic(optional = false)
	@Column(name = "qtd_votos_nao", nullable = false, columnDefinition="Coluna que representa a quantidade de votos não que a Pauta Obteve.")
	private Long qtdVotosNao = 0L;
	
	@Basic(optional = false)
	@Column(name = "qtd_votos_sim", nullable = false, columnDefinition="Coluna que representa a quantidade de votos sim que a Pauta Obteve.")
	private Long qtdVotosSim = 0L;

	@Basic(optional = false)
	@Column(name = "ativo", nullable = false, columnDefinition="Coluna que representa o Status da Pauta 0 = 'Inativa' e 1 = 'Ativa'.")
	private boolean ativo = true;

	@Basic(optional = false)
	@Column(name = "dth_criacao", nullable = false, columnDefinition="Coluna que representa a Data e Hora da criação da Pauta.")	
	private LocalDateTime dthCriacao = LocalDateTime.now();

	@Basic(optional = false)
	@Column(name = "status", nullable = false, columnDefinition="Coluna que representa o status da Pauta sendo eles 'AGUARDANDO_RESULTADO', 'APROVADA', 'REPROVADA', 'EMPATADA'.")	
	@Enumerated(EnumType.STRING)
	private PautaStatusEnum status;
	
	public Pauta(PautaDTO dto) {
		this.id = dto.getId();
		this.descricao = dto.getDescricao();
		this.qtdVotosNao = dto.getQtdVotosNao();
		this.qtdVotosSim = dto.getQtdVotosSim();
		this.ativo = dto.isAtivo();
		this.dthCriacao = dto.getDthCriacao();
		this.status = dto.getStatus();
	} 
	
}
