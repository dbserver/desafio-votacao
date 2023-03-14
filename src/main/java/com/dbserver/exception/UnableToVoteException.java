package com.dbserver.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class UnableToVoteException extends ResponseStatusException {
    public UnableToVoteException() {
        super(NOT_FOUND, "UNABLE_TO_VOTE");
    }
}
