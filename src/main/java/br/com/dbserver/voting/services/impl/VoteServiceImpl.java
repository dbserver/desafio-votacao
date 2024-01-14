package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.dtos.CpfValidationResponseDTO;
import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.dtos.vote.VoteResponseDTO;
import br.com.dbserver.voting.enums.StatusCpfEnum;
import br.com.dbserver.voting.enums.TypeVoteEnum;
import br.com.dbserver.voting.exceptions.UnableVoteException;
import br.com.dbserver.voting.exceptions.VotingException;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.Vote;
import br.com.dbserver.voting.models.VotingSession;
import br.com.dbserver.voting.repositories.VoteRepository;
import br.com.dbserver.voting.services.CpfValidationService;
import br.com.dbserver.voting.services.VoteService;
import br.com.dbserver.voting.services.VotingCacheService;
import br.com.dbserver.voting.services.impl.vote.*;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

        validateCpf(voteRequestDTO.cpf());
        validateVote(votingSession, associateOptional);

        return votingSession.map(session -> {
            Associate associate = associateOptional.orElseThrow();
            Vote vote = new Vote(null, associate, session.getSchedule(), getVote(voteRequestDTO.vote()));
            Vote voteSaved = voteRepository.save(vote);


            return new VoteResponseDTO(
                    session.getSchedule().getTitle(),
                    associate.getName(),
                    voteSaved.getTypeVote().name());
        }).orElseThrow(() -> new VotingException("Não foi possível realizar a votação, tente novamente mais tarde"));
    }

    private void validateCpf(String cpf) {
        ResponseEntity<CpfValidationResponseDTO> entity = cpfValidationService.validateCpf(cpf);
        if(entity.getBody() != null && entity.getStatusCode().value() == HttpStatus.NOT_FOUND.value()){
            throw new UnableVoteException(Objects.requireNonNull(entity.getBody()).status());
        }
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
