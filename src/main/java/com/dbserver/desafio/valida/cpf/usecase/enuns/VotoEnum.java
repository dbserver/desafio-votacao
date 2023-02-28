package com.dbserver.desafio.valida.cpf.usecase.enuns;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VotoEnum {

    SIM("Sim"),
    NAO("Não");

    public final String value;

    VotoEnum(final String value) {
        this.value = value;
    }

    @JsonValue
    public String toValue() {
        return value;
    }
    }
