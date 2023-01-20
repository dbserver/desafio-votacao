package br.com.aplicationvotacao.aplicationvotacao.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VotoEnum {

    NAO(false),
    SIM(true);

    boolean key;
}

