package com.desafio.votacao.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(optional = false)
	@Column(name = "nome", nullable = false, length = 150)
	private String nome;

	@Basic(optional = false)
	@Column(name = "cpf", nullable = false, unique = true, length = 14)
	private String cpf;
 
}
