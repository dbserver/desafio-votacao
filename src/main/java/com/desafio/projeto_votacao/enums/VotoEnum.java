package com.desafio.projeto_votacao.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VotoEnum {
    SIM("Sim"),
    NAO("Não");

    private final String descricao;
}
