package com.dbserver.desafio.votacao.endpoint.dto;

import com.dbserver.desafio.votacao.usecase.enuns.VotoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class VotoDTO {

    private String cpfAssociado;
    private Integer idPauta;
    private VotoEnum voto;
}
