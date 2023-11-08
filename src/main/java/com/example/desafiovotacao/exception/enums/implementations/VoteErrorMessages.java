package com.example.desafiovotacao.exception.enums.implementations;

import com.example.desafiovotacao.exception.enums.interfaces.ErrorMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VoteErrorMessages implements ErrorMessages {

    SESSION_CLOSED_MESSAGE("Sessão de votação encerrada"),
    CPF_ALREADY_VOTED_ON_SESSION("Este associado já votou nesta sessão");

    private String description;

    @Override
    public String getDescription() {
        return this.description;
    }

}
