package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.dtos.vote.VoteResponseDTO;
import br.com.dbserver.voting.enums.TypeVoteEnum;
import br.com.dbserver.voting.exceptions.VotingException;
import br.com.dbserver.voting.models.Associate;
import br.com.dbserver.voting.models.vote.*;
import br.com.dbserver.voting.models.VotingSession;
import br.com.dbserver.voting.repositories.VoteRepository;
import br.com.dbserver.voting.services.VoteService;
import br.com.dbserver.voting.services.VotingCacheService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final VotingCacheService votingCacheService;

    public VoteServiceImpl(VoteRepository voteRepository, VotingCacheService votingCacheService) {
        this.voteRepository = voteRepository;
        this.votingCacheService = votingCacheService;
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "votingSessions", key = "#voteRequestDTO.idSessionVoting()"),
            @CacheEvict(value = "associates", key = "#cpf"),
            @CacheEvict(value = "voteProgress")
    })
    public VoteResponseDTO voting(VoteRequestDTO voteRequestDTO) {
        AtomicReference<VoteResponseDTO> voteResponseDTO = new AtomicReference<>(new VoteResponseDTO("", "", "", ""));
        Optional<VotingSession> votingSession = votingCacheService.getCachedVotingSession(voteRequestDTO.idSessionVoting());
        Optional<Associate> associateOptional = votingCacheService.getCachedAssociate(voteRequestDTO.cpf());

        validateVote(votingSession, associateOptional);

        votingSession.ifPresentOrElse(
                session -> {
                    Associate associate = associateOptional.orElseThrow();
                    Vote vote = new Vote(null, associate, session.getSchedule(), getVote(voteRequestDTO.vote()));
                    Vote voteSaved = voteRepository.save(vote);
                    voteResponseDTO.set(new VoteResponseDTO(
                            session.getSchedule().getTitle(),
                            associate.getName(), voteSaved.getTypeVote().name(), ""));

                },
                () -> {
                    throw new VotingException("Não foi possível realizar a votação, tente novamente mais tarde");
                }
        );

        return voteResponseDTO.get();
    }

    @Override
    public List<ResultOfTheVoteDTO> listVoteInProgress() {
        return votingCacheService.voteProgress()
                .map(List::of)
                .orElseGet(ArrayList::new);
    }


    private void validateVote(Optional<VotingSession> votingSession, Optional<Associate> associate) {

        ValidatorVote validatorVote = new DoubleVoteAssociate(new AssociateNotFound(new VotingClose(new VoteOutOfHours())), voteRepository);
        validatorVote.valid(votingSession, associate);
    }

    private TypeVoteEnum getVote(String vote) {
        return StringUtils.hasText(vote) && vote.equalsIgnoreCase("SIM") ? TypeVoteEnum.SIM : TypeVoteEnum.NAO;
    }

}
