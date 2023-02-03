package com.br.dbserver.votacao.controller.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAbrirSessao(
		@NotNull
		Long pautaId,
		int duracao	) {
	

}
