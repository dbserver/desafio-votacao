package br.com.dbserver.voting.exceptions;

public class UnableVoteException extends RuntimeException{

    public UnableVoteException(String message) {
        super(message);
    }
}
