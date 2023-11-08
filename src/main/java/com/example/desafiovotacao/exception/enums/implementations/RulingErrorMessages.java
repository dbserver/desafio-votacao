package com.example.desafiovotacao.exception.enums.implementations;

import com.example.desafiovotacao.exception.enums.interfaces.ErrorMessages;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RulingErrorMessages implements ErrorMessages {

    RULING_DOES_NOT_EXIST("A pauta informada não está cadastrada"),
    RULING_HAS_ALREADY_ENDED("A pauta já realizou uma sessão de votação e contabilizou um resultado"),
    VOTING_TIE("A contabilização não pode ser realizada por conta de um empate na votação. Realize outra sessão e contabilize novamente");

    private String description;

    @Override
    public String getDescription() {
        return this.description;
    }

}
