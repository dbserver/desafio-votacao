package com.challenge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionStatusEnum {
    OPEN("Aberta"),
    CLOSE("Fechada");

    private final String description;
}
