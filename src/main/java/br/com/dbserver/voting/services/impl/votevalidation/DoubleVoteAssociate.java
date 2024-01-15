package br.com.dbserver.voting.services.impl.votevalidation;

import br.com.dbserver.voting.exceptions.ExistingResourceException;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.VotingSession;
import br.com.dbserver.voting.repositories.VoteRepository;

import java.util.Optional;

public class DoubleVoteAssociate extends ValidatorVote {

    private final VoteRepository voteRepository;

    public DoubleVoteAssociate(ValidatorVote next, VoteRepository voteRepository) {
        super(next);
        this.voteRepository = voteRepository;
    }

    @Override
    public boolean valid(Optional<VotingSession> votingSession, Optional<Associate> associate) {

        if(associate.isPresent() && votingSession.isPresent()){
            if(voteRepository.existsByAssociateAndVotingSession(associate.get(), votingSession.get())){
                throw new ExistingResourceException("Associado ja fez seu voto");
            }
        }

        return next.valid(votingSession, associate);
    }
}
