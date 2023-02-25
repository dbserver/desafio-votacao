package com.dbserver.desafio.votacao.usecase.domain;

import lombok.Data;

@Data
public class Voto {

    private Associado associado;
    private Pauta pauta;
    private String voto;
}
