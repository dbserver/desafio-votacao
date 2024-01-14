package br.com.dbserver.voting.services.impl.vote;

import br.com.dbserver.voting.exceptions.VotingException;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.VotingSession;

import java.time.LocalDateTime;
import java.util.Optional;

public class VoteOutOfHours extends ValidatorVote {
    public VoteOutOfHours() {
        super(null);
    }

    @Override
    public boolean valid(Optional<VotingSession> votingSession, Optional<Associate> associate) {
        if(votingSession.isPresent() && isOutsideRange(LocalDateTime.now(), votingSession.get().getStart(), votingSession.get().getEnd())){
            throw new VotingException("Voto fora do horario permitido pela pauta");
        }
        return false;
    }

    private boolean isOutsideRange(LocalDateTime dateTime, LocalDateTime startRange, LocalDateTime endRange) {
        return dateTime.isBefore(startRange) || dateTime.isAfter(endRange);
    }
}
