package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.helpers.Util;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.VotingSession;
import br.com.dbserver.voting.repositories.AssociateRepository;
import br.com.dbserver.voting.repositories.VotingSessionRepository;
import br.com.dbserver.voting.services.VotingCacheService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VotingCacheServiceImpl implements VotingCacheService {

    private final VotingSessionRepository votingSessionRepository;
    private final AssociateRepository associateRepository;
    public VotingCacheServiceImpl(VotingSessionRepository votingSessionRepository, AssociateRepository associateRepository) {
        this.votingSessionRepository = votingSessionRepository;
        this.associateRepository = associateRepository;
    }

    @Override
    @Cacheable(value = "votingSession")
    public Optional<VotingSession> getCachedVotingSession(String sessionId) {
        return votingSessionRepository.findById(UUID.fromString(sessionId));
    }

    @Override
    @Cacheable(value = "associate")
    public Optional<Associate> getCachedAssociate(String cpf) {
        String cpfAssociate = Util.removeNonNumericCharacterFromCpf(cpf);
        return associateRepository.findByCpf(cpfAssociate);
    }

    @Override
    @Cacheable(value = "voteProgress")
    public List<ResultOfTheVoteDTO> voteProgress() {
        return votingSessionRepository.voteProgress().stream().toList();
    }

}
