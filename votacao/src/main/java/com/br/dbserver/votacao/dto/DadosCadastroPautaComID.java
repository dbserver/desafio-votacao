package com.br.dbserver.votacao.dto;

import jakarta.validation.Valid;

public record DadosCadastroPautaComID(
		String tema,
		long id
		
) {
	
}
