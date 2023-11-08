package com.example.desafiovotacao.exception;

import com.example.desafiovotacao.exception.enums.interfaces.ErrorMessages;

public class ValidationExceptions extends RuntimeException{
    public ValidationExceptions(ErrorMessages message) {
        super(message.getDescription());
    }
}
