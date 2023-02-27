package com.dbserver.desafio.votacao.endpoint.dto;

import lombok.Getter;
import lombok.experimental.FieldNameConstants;

@Getter
@FieldNameConstants
public class PautaDuracaoDTO {

    private Integer idPauta;
    private Integer duracaoSessao;
}
