package com.desafio.votacao.dto;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "VotoDTO")
public class VotoDTO implements Serializable {

	private static final long serialVersionUID = 597022010845966821L;

	@Schema(description = "Identificador do associado")
    private Long idAssociado;

	@Schema(description = "Identificador da pauta")
    private Long idPauta;

	@Schema(description = "Valor do voto")
    private String voto;
}
