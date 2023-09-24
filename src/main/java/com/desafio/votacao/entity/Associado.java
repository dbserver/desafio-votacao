package com.desafio.votacao.entity;

import com.desafio.votacao.dto.AssociadoDTO;

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
	@Column(name = "nome", nullable = false, length = 150, columnDefinition="Coluna que representa o Nome do Associado sendo limitado em 150 caracteres.")
	private String nome;

	@Basic(optional = false)
	@Column(name = "cpf", nullable = false, unique = true, length = 11, columnDefinition="Coluna que representa o CPF do Associado sendo limitado em 11 caracteres.")
	private String cpf;


	public Associado(AssociadoDTO dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.cpf =  dto.getCpf();
	}
	
}
