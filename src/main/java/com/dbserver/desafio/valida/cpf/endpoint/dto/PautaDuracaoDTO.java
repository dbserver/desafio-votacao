package com.dbserver.desafio.valida.cpf.endpoint.dto;

import lombok.Getter;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@FieldNameConstants
public class PautaDuracaoDTO {

    @NotNull
    private Integer idPauta;

    private Integer duracaoSessao;
}
