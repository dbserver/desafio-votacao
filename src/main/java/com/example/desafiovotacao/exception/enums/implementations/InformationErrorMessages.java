package com.example.desafiovotacao.exception.enums.implementations;

import com.example.desafiovotacao.exception.enums.interfaces.ErrorMessages;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum InformationErrorMessages implements ErrorMessages {

    FAULTY_INFORMATION("Os dados fornecidos não estão de acordo com as obrigatoriedades desta requisição"),
    INVALID_CPF("O CPF informado é invalido. Verifique seus dados");

    private String description;

    @Override
    public String getDescription() {
        return this.description;
    }

}
