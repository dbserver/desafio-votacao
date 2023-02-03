package com.br.dbserver.votacao.dto;

import com.br.dbserver.votacao.model.TipoVoto;

import jakarta.validation.constraints.NotNull;

public record DadosVoto(
	@NotNull
	Long associadoId,
	@NotNull
	Long pautaId,
	@NotNull
	TipoVoto tipoVoto
		) {



}
