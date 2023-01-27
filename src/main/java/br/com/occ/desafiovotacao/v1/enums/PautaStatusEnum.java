package br.com.occ.desafiovotacao.v1.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PautaStatusEnum {
    APROVADA(1, "Aprovada"),
    NAO_APROVADA(2, "Não Aprovada"),
    EMPATADA(3, "Empatada");

    private final int id;
    private final String value;

    PautaStatusEnum(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
