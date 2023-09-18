package com.desafio.projeto_votacao.exceptions;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {

    private List<FieldError> fieldErrors;

    public ValidationErrorResponse(int errorCode, String message, List<FieldError> fieldErrors) {
        super(errorCode, message);
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
