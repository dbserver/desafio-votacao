package com.fernandesclaudi.desafiovotacao.enums;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;

public enum VotoEnum {
    SIM("S"),
    NAO("N");

    private final String value;

    VotoEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
