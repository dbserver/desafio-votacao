package com.fernandesclaudi.desafiovotacao.enums;


import com.fasterxml.jackson.annotation.JsonValue;

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

    public static VotoEnum findByValue(String value){
        for(VotoEnum v : values()){
            if( v.value.equals(value)){
                return v;
            }
        }
        return null;
    }

}
