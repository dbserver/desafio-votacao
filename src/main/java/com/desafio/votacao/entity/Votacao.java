package com.desafio.votacao.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Votacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Pauta pauta;

	@OneToMany
	private List<Voto> votos;
	
	@Basic(optional = false)
	@Column(name = "dth_criacao", nullable = false, columnDefinition="Coluna que representa a Data e Hora do Inicio da Votação.")	
	@CreationTimestamp
	private LocalDateTime dthCriacao;
	
	@Basic(optional = false)
	@Column(name = "dth_fim", nullable = false, columnDefinition="Coluna que representa a Data e Hora Final da Votação.")	
	private LocalDateTime dthFim = calcularDataFim();

	@Basic(optional = false)
	@Column(name = "ativo", nullable = false, columnDefinition="Coluna que representa o Status da Votação 0 = 'Inativa' e 1 = 'Ativa'.")
	private boolean ativo = true;
	
	private LocalDateTime calcularDataFim() { 
		return LocalDateTime.now().plusMinutes(1);
	}
	
	
}
