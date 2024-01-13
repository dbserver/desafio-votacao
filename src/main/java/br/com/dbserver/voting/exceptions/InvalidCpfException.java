package br.com.dbserver.voting.exceptions;

public class InvalidCpfException extends RuntimeException {

    public InvalidCpfException(String message) {
        super(message);
    }
}
