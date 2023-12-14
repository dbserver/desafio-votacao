package com.challenge.votation.model;

import lombok.Data;

import java.util.List;

@Data
public class Error {
    private final String message;
    private final int code;
    private final String status;
    private final String object;
    private final List<ErrorObject> errors;
}
