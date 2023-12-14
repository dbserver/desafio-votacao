package com.challenge.votation.exception;

public class VotationNotFoundException extends RuntimeException{
    public VotationNotFoundException(String message) {
        super(message);
    }
}
