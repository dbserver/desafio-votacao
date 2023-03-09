package com.dbserver.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ApiExceptionSchema {
    private final String message;
    private final Integer status;
    private final String error;
    private final ZonedDateTime timestamp;
}
