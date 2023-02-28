package com.dbserver.desafio.valida.cpf.endpoint.dto;

import com.dbserver.desafio.valida.cpf.usecase.enuns.VotoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class VotoDTO {

    @NotBlank
    private String cpfAssociado;

    @NotNull
    private Integer idPauta;

    @NotNull
    private VotoEnum voto;
}
