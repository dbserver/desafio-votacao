package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.converters.vottingsession.VotingSessionToVotingSessionResponseDtoMapper;
import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.exceptions.ExistingResourceException;
import br.com.dbserver.voting.exceptions.NotFoundException;
import br.com.dbserver.voting.exceptions.VotingException;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.VotingSession;
import br.com.dbserver.voting.repositories.ScheduleRepository;
import br.com.dbserver.voting.repositories.VotingSessionRepository;
import br.com.dbserver.voting.services.VotingSessionService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

    final VotingSessionRepository votingSessionRepository;
    final ScheduleRepository scheduleRepository;


    final VotingSessionToVotingSessionResponseDtoMapper votingSessionToVotingSessionResponseDtoMapper;

    public VotingSessionServiceImpl(VotingSessionRepository votingSessionRepository, ScheduleRepository scheduleRepository, VotingSessionToVotingSessionResponseDtoMapper votingSessionToVotingSessionResponseDtoMapper) {
        this.votingSessionRepository = votingSessionRepository;
        this.scheduleRepository = scheduleRepository;
        this.votingSessionToVotingSessionResponseDtoMapper = votingSessionToVotingSessionResponseDtoMapper;
    }

    @Override
    @Transactional

    @Caching(evict = {
            @CacheEvict(value = "associate", allEntries = true),
            @CacheEvict(value = "voteProgress", allEntries = true),
            @CacheEvict(value = "votingSession", allEntries = true),
            @CacheEvict(value = {"votingSessions"}, allEntries = true)
    })
    public VotingSessionResponseDTO openVoting(VotingSessionRequestDTO votingSessionRequestDTO) {
        Optional<Schedule> schedule = scheduleRepository.findById(UUID.fromString(votingSessionRequestDTO.scheduleId()));

        if (schedule.isEmpty()) {
            // Trate o caso em que o cronograma não existe
            throw new NotFoundException("Pauta não encontrada");
        }

        if (votingSessionRepository.existsByScheduleIdAndStatus(schedule.get().getId(), StatusVotingSessionEnum.OPEN)) {
            throw new ExistingResourceException("Votação em andamento para essa pauta, não é possível criar outra");
        }

        VotingSession votingSession = votingTime(StringUtils.hasText(votingSessionRequestDTO.votingEndTime())
                ? Integer.parseInt(votingSessionRequestDTO.votingEndTime()) : 1);

        votingSession.setStatus(StatusVotingSessionEnum.OPEN);
        votingSession.setSchedule(schedule.get());

        VotingSession votingSessionSaved = votingSessionRepository.save(votingSession);

        return votingSessionToVotingSessionResponseDtoMapper.map(votingSessionSaved, new VotingSessionResponseDTO());
    }

    @Override
    @Cacheable(value = "votingSessions")
    public Page<VotingSessionResponseDTO> listAll(Pageable pageable) {
        Page<VotingSession> allSessions = votingSessionRepository.findAll(pageable);
        VotingSessionResponseDTO votingSessionResponseDTO = new VotingSessionResponseDTO();

        List<VotingSessionResponseDTO> votingSessions = allSessions
                .stream()
                .map(votingSession -> votingSessionToVotingSessionResponseDtoMapper.map(votingSession, votingSessionResponseDTO))
                .collect(Collectors.toList());

        return new PageImpl<>(votingSessions, pageable, allSessions.getTotalPages());
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "votingSession", allEntries = true),
            @CacheEvict(value = "votingSessions", allEntries = true),
            @CacheEvict(value = "voteProgress", allEntries = true)
    })
    public ResultOfTheVoteDTO closeVoting(String sessionId) {
        VotingSession votingSession = votingSessionRepository.findById(UUID.fromString(sessionId))
                .orElseThrow(() -> new NotFoundException("Sessao de votos nao encontrada"));

        if (votingSession.getStatus() == StatusVotingSessionEnum.CLOSE) {
            throw new VotingException("A sessão de votação já está fechada.");
        }

        votingSession.setStatus(StatusVotingSessionEnum.CLOSE);
        votingSessionRepository.save(votingSession);

        List<ResultOfTheVoteDTO> resultOfTheVoteDTO = votingSessionRepository.voteProgress();

        // Retorna o primeiro resultado encontrado
        return resultOfTheVoteDTO.stream().findFirst().orElse(null);
    }


    private VotingSession votingTime(int durationMinutes) {
        VotingSession votingSession = new VotingSession();

        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime endDateTime = currentDateTime.plusMinutes(durationMinutes);

        votingSession.setStart(currentDateTime);
        votingSession.setEnd(endDateTime);

        return votingSession;
    }

}
