package com.desafio.votacao.dto;

import java.io.Serializable;

import com.desafio.votacao.enums.VotoEnumDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "VotacaoDTO")
public class VotacaoDTO implements Serializable {

	private static final long serialVersionUID = 597022010845966821L;

	@Schema(description = "Objeto associado")
    private AssociadoBasicoDTO associado;

	@Schema(description = "Objeto pauta")
    private PautaBasicoDTO pauta;

	@Schema(description = "Valor do voto")
    private VotoEnumDTO voto;
}
