package com.desafio.votacao.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VotacaoRequest {

	@NotNull(message = "Pauta ID não pode ser null")
	private Long pautaId;

	public VotacaoRequest(@NotNull(message = "Pauta ID não pode ser null") Long pautaId) {
		super();
		this.pautaId = pautaId;
	} 
	
	
}
