package br.com.dbserver.voting.models.vote;

import br.com.dbserver.voting.exceptions.NotFoundException;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.VotingSession;

import java.util.Optional;

public class AssociateNotFound extends ValidatorVote {
    public AssociateNotFound(ValidatorVote next) {
        super(next);
    }

    @Override
    public boolean valid(Optional<VotingSession> votingSession, Optional<Associate> associate) {
        if(associate.isEmpty()){
            throw new NotFoundException("Associado nao encontrado");
        }

        return next.valid(votingSession, associate);
    }
}
