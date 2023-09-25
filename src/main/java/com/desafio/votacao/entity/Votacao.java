package com.desafio.votacao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "votacao")
public class Votacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Pauta pauta;
	
	/**
	 * Coluna que representa a Data e Hora do Inicio da Votação.
	 */
	@Basic(optional = false)
	@Column(name = "dth_criacao", nullable = false)	
	private LocalDateTime dthCriacao = LocalDateTime.now();
	
	/**
	 * Coluna que representa a Data e Hora Final da Votação.
	 */
	@Basic(optional = false)
	@Column(name = "dth_fim", nullable = false)	
	private LocalDateTime dthFim = calcularDataFim();

	/**
	 * Coluna que representa o Status da Votação 0 = 'Inativa' e 1 = 'Ativa'.
	 */
	@Basic(optional = false)
	@Column(name = "ativo", nullable = false)
	private boolean ativo = true;
	
	private LocalDateTime calcularDataFim() { 
		return LocalDateTime.now().plusMinutes(1);
	}

	public Votacao() {
	}
	
	
	
}
