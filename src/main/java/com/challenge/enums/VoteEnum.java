package com.challenge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteEnum {
    YES("Sim"),
    NO("Não");

    private final String description;
}
