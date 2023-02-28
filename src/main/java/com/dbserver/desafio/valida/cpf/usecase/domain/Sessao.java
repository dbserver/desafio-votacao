package com.dbserver.desafio.valida.cpf.usecase.domain;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Sessao {

    private LocalDateTime inicio;
    private Integer duracao;
}
