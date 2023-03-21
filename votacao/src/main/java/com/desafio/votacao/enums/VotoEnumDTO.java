package com.desafio.votacao.enums;

import lombok.Getter;

@Getter
public class VotoEnumDTO {
    
    SIM("Sim"),
    NAO("Não");

    private String valor;

    VotoEnumDTO(String valor) { 
        this.valor = valor;
    }
}
