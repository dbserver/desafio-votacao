package com.dbserver.desafio.valida.cpf.endpoint.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class VotosPautaDTO {

    private Integer totalVotosSim;

    private Integer totalVotosNao;

    private PautaDTO pauta;
}
