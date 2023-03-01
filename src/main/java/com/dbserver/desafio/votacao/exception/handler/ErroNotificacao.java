package com.dbserver.desafio.votacao.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ErroNotificacao {

    private String mensagem;
}
