package br.com.dbserver.voting.exceptions;

public class UnableVoteExceptionDetails {
    private String status;

    public UnableVoteExceptionDetails(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
