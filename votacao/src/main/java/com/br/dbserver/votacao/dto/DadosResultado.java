package com.br.dbserver.votacao.dto;

import java.time.LocalDateTime;


public record DadosResultado(
		long idPauta,
		String tema,
		LocalDateTime inicioVotacao,
		LocalDateTime fimVotacao,
		String resultadoVotacao
		) {


}
