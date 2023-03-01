package com.dbserver.desafio.votacao.endpoint.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class PautaIdDTO {

    @NotNull
    private Integer idPauta;
}
