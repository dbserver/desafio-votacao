package com.dbserver.desafio.valida.cpf.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class ErroNotificacao {

    private String mensagem;
}
