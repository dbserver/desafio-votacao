package br.com.dbserver.voting.services.impl;

import br.com.dbserver.voting.converters.vottingsession.VotingSessionToVotingSessionResponseDtoMapper;
import br.com.dbserver.voting.dtos.ScheduleDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import br.com.dbserver.voting.exceptions.ExistingResourceException;
import br.com.dbserver.voting.models.Schedule;
import br.com.dbserver.voting.models.VotingSession;
import br.com.dbserver.voting.repositories.ScheduleRepository;
import br.com.dbserver.voting.repositories.VotingSessionRepository;
import br.com.dbserver.voting.services.VotingSessionService;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = {"list-voting-session"}, allEntries = true)
    public VotingSessionResponseDTO openVoting(VotingSessionRequestDTO votingSessionRequestDTO) {

        Optional<Schedule> schedule = scheduleRepository.findById(UUID.fromString(votingSessionRequestDTO.scheduleId()));

        VotingSessionResponseDTO votingSessionResponse = new VotingSessionResponseDTO(
                UUID.randomUUID(),
                new ScheduleDTO(UUID.randomUUID(), ""),
                "",
                "",
                "");

        if (schedule.isPresent()) {

            if(votingSessionRepository.existsByScheduleIdAndStatus(schedule.get().getId(), StatusVotingSessionEnum.OPEN)){
                throw new ExistingResourceException("Votação em andamento para essa pauta, nao é possivel criar outra");
            }

            VotingSession votingSession = votingTime(1);

            if (StringUtils.hasText(votingSessionRequestDTO.votingEndTime())) {
                votingSession = votingTime(Integer.parseInt(votingSessionRequestDTO.votingEndTime()));
            }

            votingSession.setStatus(StatusVotingSessionEnum.OPEN);
            votingSession.setSchedule(schedule.get());
            VotingSession votingSessionSaved = votingSessionRepository.save(votingSession);
            votingSessionResponse = votingSessionToVotingSessionResponseDtoMapper.map(votingSessionSaved, votingSessionResponse);
        }

        return votingSessionResponse;
    }

    @Override
    @Cacheable(value = "list-voting-session")
    public Page<VotingSessionResponseDTO> listAll(Pageable pageable) {
        Page<VotingSession> allSessions = votingSessionRepository.findAll(pageable);
        VotingSessionResponseDTO votingSessionResponseDTO = new VotingSessionResponseDTO(
                UUID.randomUUID(),
                new ScheduleDTO(UUID.randomUUID(), ""),
                "",
                "",
                "");

        List<VotingSessionResponseDTO> votingSessions = allSessions
                .stream()
                .map(votingSession -> votingSessionToVotingSessionResponseDtoMapper.map(votingSession, votingSessionResponseDTO))
                .collect(Collectors.toList());

        return new PageImpl<>(votingSessions, pageable, allSessions.getTotalPages());
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
