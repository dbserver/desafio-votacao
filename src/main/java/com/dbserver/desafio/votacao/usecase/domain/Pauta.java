package com.dbserver.desafio.votacao.usecase.domain;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Pauta {

    private Integer idPauta;
    private String nome;
    private String descricao;
    private Sessao sessao;
}
