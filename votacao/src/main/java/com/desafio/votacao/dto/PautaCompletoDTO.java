package com.desafio.votacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PautaCompletoDTO")
public class PautaCompletoDTO extends PautaBasicoDTO {

	private static final long serialVersionUID = -1120048987206985201L;

	@Schema(description = "Identificador da pauta")
    private Long id;

	@Schema(description = "Pauta excluída")
    private boolean flExcluida;
}
