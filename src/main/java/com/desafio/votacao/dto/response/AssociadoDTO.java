package com.desafio.votacao.dto.response;

import com.desafio.votacao.entity.Associado;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AssociadoDTO {
	private Long id;
	private String nome;
	private String cpf;
	
	public AssociadoDTO(Associado entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.cpf =  entity.getCpf();
	}

	public AssociadoDTO(Long id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}
}
