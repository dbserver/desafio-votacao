package com.br.dbserver.votacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPauta(
		@NotBlank
		String tema 
) {	
}
