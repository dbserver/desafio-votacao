package com.desafio.votacao.entity;

import java.time.LocalDateTime;

import com.desafio.votacao.dto.response.PautaDTO;
import com.desafio.votacao.enums.PautaStatusEnum;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pauta")
@NoArgsConstructor
public class Pauta { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Coluna que representa a descrição da Pauta.
	 */
	@Basic(optional = false)
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	/**
	 * Coluna que representa a quantidade de votos não que a Pauta Obteve.
	 */
	@Basic(optional = false)
	@Column(name = "qtd_votos_nao", nullable = false)
	private Long qtdVotosNao = 0L;
	
	/**
	 * Coluna que representa a quantidade de votos sim que a Pauta Obteve.
	 */
	@Basic(optional = false)
	@Column(name = "qtd_votos_sim", nullable = false)
	private Long qtdVotosSim = 0L;

	/**
	 * Coluna que representa o Status da Pauta 0 = 'Inativa' e 1 = 'Ativa'.
	 */
	@Basic(optional = false)
	@Column(name = "ativo", nullable = false)
	private boolean ativo = true;

	/**
	 * Coluna que representa a Data e Hora da criação da Pauta.
	 */
	@Basic(optional = false)
	@Column(name = "dth_criacao", nullable = false)	
	private LocalDateTime dthCriacao = LocalDateTime.now();

	/**
	 * Coluna que representa o status da Pauta sendo eles 'AGUARDANDO_RESULTADO', 'APROVADA', 'REPROVADA', 'EMPATADA'.
	 */
	@Basic(optional = false)
	@Column(name = "status", nullable = false)	
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
