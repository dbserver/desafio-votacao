package br.com.dbserver.votacao.v1.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusUsuario {
    PODE_VOTAR("PODE VOTAR"),
    NAO_PODE_VOTAR("NÃO PODE VOTAR");

    private final String valor;

    StatusUsuario( String value) {
        this.valor = value;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }
}