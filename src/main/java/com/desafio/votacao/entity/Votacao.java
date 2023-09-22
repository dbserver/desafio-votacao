package com.desafio.votacao.entity;

import java.time.LocalDateTime;
import java.util.List;

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
	private List<Associado> associado;
	
	@Basic(optional = false)
	@Column(name = "dth_criacao", nullable = false)	
	private LocalDateTime dthCriacao = LocalDateTime.now();
	
	@Basic(optional = false)
	@Column(name = "dth_fim", nullable = false)	
	private LocalDateTime dthFim = calcularDataFim();

	@Basic(optional = false)
	@Column(name = "ativo", nullable = false)
	private boolean ativo = true;
	
	private LocalDateTime calcularDataFim() { 
		return LocalDateTime.now().plusMinutes(1);
	}
	
	
}
