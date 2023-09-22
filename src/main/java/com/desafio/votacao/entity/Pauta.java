package com.desafio.votacao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Basic(optional = false)
	@Column(name = "ativo", nullable = false)
	private boolean ativo = true;

	@Basic(optional = false)
	@Column(name = "dth_criacao", nullable = false)	
	private LocalDateTime dthCriacao = LocalDateTime.now();
}
