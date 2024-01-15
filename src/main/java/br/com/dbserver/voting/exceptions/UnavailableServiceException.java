package br.com.dbserver.voting.exceptions;

public class UnavailableServiceException extends RuntimeException{
    public UnavailableServiceException(String message) {
        super(message);
    }
}
