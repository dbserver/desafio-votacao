package com.dbserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EntityAlreadyExistsException extends ResponseStatusException {
    public EntityAlreadyExistsException(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }
}
