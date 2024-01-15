package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.dtos.vote.VoteResponseDTO;
import br.com.dbserver.voting.enums.TypeVoteEnum;
import br.com.dbserver.voting.exceptions.VotingException;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.Vote;
import br.com.dbserver.voting.models.VotingSession;
import br.com.dbserver.voting.repositories.VoteRepository;
import br.com.dbserver.voting.services.CpfValidationService;
import br.com.dbserver.voting.services.VoteService;
import br.com.dbserver.voting.services.VotingCacheService;
import br.com.dbserver.voting.services.impl.votevalidation.*;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final VotingCacheService votingCacheService;

    private final CpfValidationService cpfValidationService;

    public VoteServiceImpl(VoteRepository voteRepository, VotingCacheService votingCacheService, CpfValidationService cpfValidationService) {
        this.voteRepository = voteRepository;
        this.votingCacheService = votingCacheService;
        this.cpfValidationService = cpfValidationService;
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "votingSession", allEntries = true),
            @CacheEvict(value = "associate", allEntries = true),
            @CacheEvict(value = "voteProgress", allEntries = true)
    })
    public VoteResponseDTO voting(VoteRequestDTO voteRequestDTO) {
        Optional<VotingSession> votingSession = votingCacheService.getCachedVotingSession(voteRequestDTO.idSessionVoting());
        Optional<Associate> associateOptional = votingCacheService.getCachedAssociate(voteRequestDTO.cpf());

        String validatedCpf = cpfValidationService.validateCpf(voteRequestDTO.cpf());
        validateVote(votingSession, associateOptional);

        return votingSession.map(session -> {
            Associate associate = associateOptional.orElseThrow();
            Vote vote = new Vote(null, associate, session.getSchedule(), session, getVote(voteRequestDTO.vote()));
            Vote voteSaved = voteRepository.save(vote);


            return new VoteResponseDTO(
                    session.getSchedule().getTitle(),
                    associate.getName(),
                    voteSaved.getTypeVote().name(),
                    validatedCpf);
        }).orElseThrow(() -> new VotingException("Não foi possível realizar a votação, tente novamente mais tarde"));
    }

    @Override
    public List<ResultOfTheVoteDTO> listVoteInProgress() {
        return votingCacheService.voteProgress();
    }

    private void validateVote(Optional<VotingSession> votingSession, Optional<Associate> associate) {
        ValidatorVote validatorVote = new DoubleVoteAssociate(new AssociateNotFound(new VotingClose(new VoteOutOfHours())), voteRepository);
        validatorVote.valid(votingSession, associate);
    }

    private TypeVoteEnum getVote(String vote) {
        return StringUtils.hasText(vote) && vote.equalsIgnoreCase("SIM") ? TypeVoteEnum.SIM : TypeVoteEnum.NAO;
    }

}
