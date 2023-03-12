package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.VotingSessionCreateDTO;
import com.dbserver.model.dto.VotingSessionDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.entity.VotingSession;
import com.dbserver.model.mapper.VotingSessionMapper;
import com.dbserver.repository.VotingSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotingSessionServiceTest {

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private VotingSessionMapper votingSessionMapper;

    @Mock
    private AgendaService agendaService;

    @InjectMocks
    private VotingSessionService votingSessionService;

    @Test
    void shouldCreateVoting() {
        VotingSession voting = getVotingMock();
        Agenda agenda = getAgendaMock();
        VotingSessionCreateDTO votingSessionCreateDTO = getVotingCreateDTOMock();
        VotingSessionDTO votingSessionDTO = getVotingDTOMock();
        when(agendaService.findById(any())).thenReturn(agenda);
        when(votingSessionMapper.toEntity(votingSessionCreateDTO)).thenReturn(voting);
        when(votingSessionRepository.save(voting)).thenReturn(voting);
        when(votingSessionMapper.toDTO(voting)).thenReturn(votingSessionDTO);
        VotingSessionDTO saved = votingSessionService.create(votingSessionCreateDTO);
        assertThat(saved, equalTo(votingSessionDTO));
    }

    @Test
    void shouldThrowConflictException() {
        VotingSession voting = getVotingMock();
        Agenda agenda = getAgendaMock();
        VotingSessionCreateDTO votingSessionCreateDTO = getVotingCreateDTOMock();
        when(agendaService.findById(any())).thenReturn(agenda);
        when(votingSessionMapper.toEntity(votingSessionCreateDTO)).thenReturn(voting);
        when(votingSessionRepository.save(voting)).thenThrow(DuplicateKeyException.class);
        ConflictException throwable =
                catchThrowableOfType(() -> votingSessionService.create(votingSessionCreateDTO), ConflictException.class);
        assertThat(throwable.getClass(), equalTo(ConflictException.class));
        assertThat(throwable.getReason(), equalTo("Voting session already started"));
    }

    @Test
    void shouldThrowBusinessException() {
        VotingSession voting = getVotingMock();
        Agenda agenda = getAgendaMock();
        VotingSessionCreateDTO votingSessionCreateDTO = getVotingCreateDTOMock();
        when(agendaService.findById(any())).thenReturn(agenda);
        when(votingSessionMapper.toEntity(votingSessionCreateDTO)).thenReturn(voting);
        when(votingSessionRepository.save(voting)).thenThrow(RuntimeException.class);
        BusinessException throwable =
                catchThrowableOfType(() -> votingSessionService.create(votingSessionCreateDTO), BusinessException.class);
        assertThat(throwable.getClass(), equalTo(BusinessException.class));
    }

    @Test
    void shouldGetVotingDTOById() {
        VotingSession voting = getVotingMock();
        VotingSessionDTO votingSessionDTO = getVotingDTOMock();
        votingSessionDTO.setId(voting.getId());
        votingSessionDTO.setIdAgenda(voting.getIdAgenda());
        when(votingSessionRepository.findById(voting.getId())).thenReturn(Optional.of(voting));
        when(votingSessionMapper.toDTO(voting)).thenReturn(votingSessionDTO);
        VotingSessionDTO found = votingSessionService.getById(voting.getId());
        assertThat(found, equalTo(votingSessionDTO));
    }

    @Test
    void shouldFindVotingById() {
        VotingSession voting = getVotingMock();
        when(votingSessionRepository.findById(voting.getId())).thenReturn(Optional.of(voting));
        VotingSession found = votingSessionService.findById(voting.getId());
        assertThat(found, equalTo(voting));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionOnFindById() {
        VotingSession voting = getVotingMock();
        when(votingSessionRepository.findById(voting.getId())).thenReturn(Optional.empty());
        EntityNotFoundException throwable =
                catchThrowableOfType(() -> votingSessionService.findById(voting.getId()), EntityNotFoundException.class);
        assertThat(throwable.getClass(), equalTo(EntityNotFoundException.class));
        assertThat(throwable.getReason(), equalTo("Voting session not found: " + voting.getId()));
    }

    @Test
    void shouldFindVotingByIdAgenda() {
        VotingSession voting = getVotingMock();
        when(votingSessionRepository.findByIdAgenda(voting.getIdAgenda())).thenReturn(Optional.of(voting));
        VotingSession found = votingSessionService.findByIdAgenda(voting.getIdAgenda());
        assertThat(found, equalTo(voting));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionOnGetByIdAgenda() {
        VotingSession voting = getVotingMock();
        when(votingSessionRepository.findByIdAgenda(voting.getIdAgenda())).thenReturn(Optional.empty());
        EntityNotFoundException throwable =
                catchThrowableOfType(() -> votingSessionService.findByIdAgenda(voting.getIdAgenda()), EntityNotFoundException.class);
        assertThat(throwable.getClass(), equalTo(EntityNotFoundException.class));
        assertThat(throwable.getReason(), equalTo("Voting session not found for agenda: " + voting.getIdAgenda()));
    }

    @Test
    void shouldReturnTrueForOpenVoting() {
        VotingSession voting = getVotingMock();
        boolean isOpen = votingSessionService.isOpen(voting);
        assertThat(isOpen, equalTo(true));
    }

    @Test
    void shouldReturnFalseForClosedVoting() {
        VotingSession voting = getVotingMock();
        voting.setEndDate(LocalDateTime.now());
        boolean isOpen = votingSessionService.isOpen(voting);
        assertThat(isOpen, equalTo(false));
    }

    private VotingSession getVotingMock() {
        long duration = 60000l;
        return VotingSession.builder()
                .id("6404ff797f24ce45b0022c83")
                .idAgenda("idAgenda01")
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plus(duration, ChronoUnit.MILLIS))
                .duration(duration)
                .build();
    }

    private Agenda getAgendaMock() {
        return Agenda.builder().build();
    }

    private VotingSessionCreateDTO getVotingCreateDTOMock() {
        return VotingSessionCreateDTO.builder().build();
    }

    private VotingSessionDTO getVotingDTOMock() {
        return VotingSessionDTO.builder().build();
    }

}
