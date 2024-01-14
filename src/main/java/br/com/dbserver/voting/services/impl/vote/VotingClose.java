package br.com.dbserver.voting.services.impl.vote;

import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.exceptions.VotingException;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.VotingSession;

import java.util.Optional;

public class VotingClose extends ValidatorVote {
    public VotingClose(ValidatorVote next) {
        super(next);
    }

    @Override
    public boolean valid(Optional<VotingSession> votingSession, Optional<Associate> associate) {
        if(votingSession.isPresent() && votingSession.get().getStatus() == StatusVotingSessionEnum.CLOSE){
            throw new VotingException("Votacao encerrada para esta pauta");
        }
        return next.valid(votingSession, associate);
    }
}
