package com.desafio.votacao.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "PautaBasicoDTO")
public class PautaBasicoDTO implements Serializable {

	private static final long serialVersionUID = 597022010845966821L;

	@Schema(description = "Descrição da pauta")
    private String descricao;

	@Schema(description = "DataInicio")
    private LocalDateTime dtInicio;

	@Schema(description = "DataFim")
    private LocalDateTime dtFim;
}
