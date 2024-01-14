package br.com.dbserver.voting.models.vote;

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
            if(voteRepository.existsByAssociateAndSchedule(associate.get(), votingSession.get().getSchedule())){
                throw new ExistingResourceException("Associado ja fez seu voto");
            }
        }

        return next.valid(votingSession, associate);
    }
}
