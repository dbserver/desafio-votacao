package com.desafio.votacao.entity;

import com.desafio.votacao.dto.response.AssociadoDTO;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "associado")
@NoArgsConstructor
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Coluna que representa o Nome do Associado sendo limitado em 150 caracteres.
	 */
	@Basic(optional = false)
	@Column(name = "nome", nullable = false, length = 150)
	private String nome;

	/**
	 * Coluna que representa o CPF do Associado sendo limitado em 11 caracteres.
	 */
	@Basic(optional = false)
	@Column(name = "cpf", nullable = false, length = 11, unique = true)
	private String cpf;


	public Associado(AssociadoDTO dto) {
		this.id = dto.getId();
		this.nome = dto.getNome();
		this.cpf =  dto.getCpf();
	}
	
}
