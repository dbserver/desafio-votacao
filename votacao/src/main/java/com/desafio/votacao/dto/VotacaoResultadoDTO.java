package com.desafio.votacao.dto;

import java.io.Serializable;
import java.util.List;

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
@Schema(name = "VotacaoResultadoDTO")
public class VotacaoResultadoDTO implements Serializable {

	private static final long serialVersionUID = 597022010845966821L;

	@Schema(description = "Objeto pauta")
    private PautaCompletoDTO pauta;

	@Schema(description = "Valor do voto")
    private VotoEnumDTO voto;

	@Schema(description = "Quantidade dos votos")
    private Integer qtdVotos;

	@Schema(description = "Lista dos associados")
    private List<AssociadoCompletoDTO> associados;
}
