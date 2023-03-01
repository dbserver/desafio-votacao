package com.dbserver.desafio.votacao.usecase.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Getter
@Builder
@EqualsAndHashCode
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
public class VotosPauta {

    private Pauta pauta;

    private Integer totalVotosSim;

    private Integer totalVotosNao;
}
