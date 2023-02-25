package com.dbserver.desafio.votacao.endpoint.dto;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class PautaDTO {

    private String nomePauta;
    private String descricaoPauta;
}
