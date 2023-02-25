package com.dbserver.desafio.votacao.usecase.domain;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Data
@FieldNameConstants
public class Sessao {

    private LocalDateTime inicio;
    private Integer duracao;
}
