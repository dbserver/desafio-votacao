package com.challenge.votation.model;

import lombok.Data;

@Data
public class ErrorObject {
    private final String message;
    private final String field;
    private final Object parameter;
}
