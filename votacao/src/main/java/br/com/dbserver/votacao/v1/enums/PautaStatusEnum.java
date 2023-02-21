package br.com.dbserver.votacao.v1.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PautaStatusEnum {
    AGUARDANDO_RESULTADO("Aguardando resultado"),
    APROVADA("Aprovada"),
    REPROVADA("Reprovada"),
    EMPATADA("Empatada");

    private final String valor;

    PautaStatusEnum(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }
}