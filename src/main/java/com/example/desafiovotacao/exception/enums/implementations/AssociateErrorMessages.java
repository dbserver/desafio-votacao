package com.example.desafiovotacao.exception.enums.implementations;

import com.example.desafiovotacao.exception.enums.interfaces.ErrorMessages;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AssociateErrorMessages implements ErrorMessages {

    ASSOCIATE_NOT_FOUNT("Associado não encontrado"),
    ASSOCIATE_ALERADY_REGISTERED("Associado já está cadastrado no sistema");

    private String descripiton;

    @Override
    public String getDescription() {
        return this.descripiton;
    }
}
