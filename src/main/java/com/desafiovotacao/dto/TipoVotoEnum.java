package com.desafiovotacao.dto;

public enum TipoVotoEnum {
    SIM("SIM"),
    NAO("NAO");

    private String tipo;

    TipoVotoEnum(String tipo) {
        this.tipo = tipo;
    }
}
