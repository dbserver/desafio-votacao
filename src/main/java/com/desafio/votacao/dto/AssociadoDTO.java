package com.desafio.votacao.dto;

import com.desafio.votacao.entity.Associado;

import lombok.Data;

@Data
public class AssociadoDTO {
	private Long id;
	private String nome;
	private String cpf;
	
	public AssociadoDTO(Associado entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cpf =  entity.getCpf();
	}
}
