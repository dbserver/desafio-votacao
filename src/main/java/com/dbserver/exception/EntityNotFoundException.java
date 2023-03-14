package com.dbserver.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class EntityNotFoundException extends ResponseStatusException {
    public EntityNotFoundException(String reason) {
        super(NOT_FOUND, reason);
    }
}
