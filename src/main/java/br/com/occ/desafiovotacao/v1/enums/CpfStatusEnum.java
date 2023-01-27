package br.com.occ.desafiovotacao.v1.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CpfStatusEnum {
    ABLE_TO_VOTE(1, "ABLE_TO_VOTE"),
    UNABLE_TO_VOTE(2, "UNABLE_TO_VOTE");

    private final int id;
    private final String value;

    CpfStatusEnum(int id, String value) {
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
