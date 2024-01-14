package br.com.dbserver.voting.services;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.VotingSession;

import java.util.List;
import java.util.Optional;

public interface VotingCacheService {
    Optional<VotingSession> getCachedVotingSession(String sessionId);
    Optional<Associate> getCachedAssociate(String cpf);

    List<ResultOfTheVoteDTO> voteProgress();
}
