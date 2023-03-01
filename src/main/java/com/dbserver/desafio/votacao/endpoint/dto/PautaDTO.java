package com.dbserver.desafio.votacao.endpoint.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class PautaDTO implements Serializable {
    private static final long serialVersionUID = 107174186166730157L;

    private Integer idPauta;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;
}
