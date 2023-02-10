package br.com.dbserver.votacao.v1.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VotoEnum {
    SIM("Sim"),
    NAO("Não");

    private final String valor;

    VotoEnum(String voto) {
        this.valor = voto;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }
}