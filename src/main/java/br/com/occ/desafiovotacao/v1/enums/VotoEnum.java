package br.com.occ.desafiovotacao.v1.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VotoEnum {
    SIM(1, "Sim"),
    NAO(2, "Não");

    private final int id;
    private final String value;

    VotoEnum(int id, String value) {
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
