package br.com.dbserver.voting.services.impl.votevalidation;

import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.VotingSession;

import java.util.Optional;

public abstract class ValidatorVote {
    protected ValidatorVote next;
    public abstract boolean valid(Optional<VotingSession> votingSession, Optional<Associate> associate);

    public ValidatorVote(ValidatorVote next) {
        this.next = next;
    }
}
