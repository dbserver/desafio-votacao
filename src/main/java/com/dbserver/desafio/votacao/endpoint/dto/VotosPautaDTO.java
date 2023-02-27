package com.dbserver.desafio.votacao.endpoint.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class VotosPautaDTO {

    private PautaDTO pauta;

    private Integer totalVotosSim;

    private Integer totalVotosNao;
}
