package com.desafio.votacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "AssociadoCompletoDTO")
public class AssociadoCompletoDTO extends AssociadoBasicoDTO {

	private static final long serialVersionUID = -1120048987206985201L;

	@Schema(description = "Identificador do associado")
    private Long id;

	@Schema(description = "Associado excluído")
    private boolean flExcluido;
}
