package com.dbserver.desafio.votacao.endpoint.dto;

import com.dbserver.desafio.votacao.usecase.domain.Sessao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@FieldNameConstants
public class SessaoDTO {

    private Integer duracaoEmMunitos;
}
