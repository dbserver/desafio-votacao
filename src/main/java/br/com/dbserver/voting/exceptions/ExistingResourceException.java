package br.com.dbserver.voting.exceptions;

public class ExistingResourceException extends RuntimeException {

    public ExistingResourceException(String message) {
        super(message);
    }
}
