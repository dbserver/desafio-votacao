package com.example.desafiovotacao.exception.enums.implementations;

import com.example.desafiovotacao.exception.enums.interfaces.ErrorMessages;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SessionErrorMessages implements ErrorMessages {

    SESSION_DOES_NOT_EXIST("A sessão informada não existe"),
    SESSION_IS_STILL_RUNNING("A sessão ainda está em andamento, aguarde a finalização dela para realizar a contagem de votos");

    private String description;

    @Override
    public String getDescription() {
        return this.description;
    }

}
