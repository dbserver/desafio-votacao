package com.challenge.exceptions;

public class VoteException extends RuntimeException {

    private static final String VOTE_ALREADY_COUNTED_MESSAGE = "Associado já votou para essa sessão!";
    private static final String CLOSED_VOTING_SESSION_MESSAGE = "Sessão fechada para voto!";

    public VoteException(String message) {
        super(message);
    }

    public static void voteAlreadyCounted() {
        throw new VoteException(VOTE_ALREADY_COUNTED_MESSAGE);
    }

    public static void closedVotingSession() {
        throw new VoteException(CLOSED_VOTING_SESSION_MESSAGE);
    }
}
