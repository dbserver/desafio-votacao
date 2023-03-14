package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.VotingSessionCreateDTO;
import com.dbserver.model.dto.VotingSessionDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.entity.VotingSession;
import com.dbserver.mapper.VotingSessionMapper;
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
        VotingSession voting = getVotingSessionMock();
        Agenda agenda = getAgendaMock();
        VotingSessionCreateDTO votingSessionCreateDTO = getVotingSessionCreateDTOMock();
        VotingSessionDTO votingSessionDTO = getVotingSessionDTOMock();
        when(agendaService.findById(any())).thenReturn(agenda);
        when(votingSessionMapper.toEntity(votingSessionCreateDTO)).thenReturn(voting);
        when(votingSessionRepository.save(voting)).thenReturn(voting);
        when(votingSessionMapper.toDTO(voting)).thenReturn(votingSessionDTO);
        VotingSessionDTO saved = votingSessionService.create(votingSessionCreateDTO);
        assertThat(saved, equalTo(votingSessionDTO));
    }

    @Test
    void shouldThrowConflictException() {
        VotingSession voting = getVotingSessionMock();
        Agenda agenda = getAgendaMock();
        VotingSessionCreateDTO votingSessionCreateDTO = getVotingSessionCreateDTOMock();
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
        VotingSession voting = getVotingSessionMock();
        Agenda agenda = getAgendaMock();
        VotingSessionCreateDTO votingSessionCreateDTO = getVotingSessionCreateDTOMock();
        when(agendaService.findById(any())).thenReturn(agenda);
        when(votingSessionMapper.toEntity(votingSessionCreateDTO)).thenReturn(voting);
        when(votingSessionRepository.save(voting)).thenThrow(new RuntimeException("Erro de teste"));
        BusinessException throwable =
                catchThrowableOfType(() -> votingSessionService.create(votingSessionCreateDTO), BusinessException.class);
        assertThat(throwable.getClass(), equalTo(BusinessException.class));
    }

    @Test
    void shouldGetVotingDTOById() {
        VotingSession voting = getVotingSessionMock();
        VotingSessionDTO votingSessionDTO = getVotingSessionDTOMock();
        votingSessionDTO.setId(voting.getId());
        votingSessionDTO.setIdAgenda(voting.getIdAgenda());
        when(votingSessionRepository.findById(voting.getId())).thenReturn(Optional.of(voting));
        when(votingSessionMapper.toDTO(voting)).thenReturn(votingSessionDTO);
        VotingSessionDTO found = votingSessionService.getById(voting.getId());
        assertThat(found, equalTo(votingSessionDTO));
    }

    @Test
    void shouldFindVotingById() {
        VotingSession voting = getVotingSessionMock();
        when(votingSessionRepository.findById(voting.getId())).thenReturn(Optional.of(voting));
        VotingSession found = votingSessionService.findById(voting.getId());
        assertThat(found, equalTo(voting));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionOnFindById() {
        VotingSession voting = getVotingSessionMock();
        when(votingSessionRepository.findById(voting.getId())).thenReturn(Optional.empty());
        EntityNotFoundException throwable =
                catchThrowableOfType(() -> votingSessionService.findById(voting.getId()), EntityNotFoundException.class);
        assertThat(throwable.getClass(), equalTo(EntityNotFoundException.class));
        assertThat(throwable.getReason(), equalTo("Voting session not found: " + voting.getId()));
    }

    @Test
    void shouldFindVotingByIdAgenda() {
        VotingSession voting = getVotingSessionMock();
        when(votingSessionRepository.findByIdAgenda(voting.getIdAgenda())).thenReturn(Optional.of(voting));
        VotingSession found = votingSessionService.findByIdAgenda(voting.getIdAgenda());
        assertThat(found, equalTo(voting));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionOnGetByIdAgenda() {
        VotingSession voting = getVotingSessionMock();
        when(votingSessionRepository.findByIdAgenda(voting.getIdAgenda())).thenReturn(Optional.empty());
        EntityNotFoundException throwable =
                catchThrowableOfType(() -> votingSessionService.findByIdAgenda(voting.getIdAgenda()), EntityNotFoundException.class);
        assertThat(throwable.getClass(), equalTo(EntityNotFoundException.class));
        assertThat(throwable.getReason(), equalTo("Voting session not found for agenda: " + voting.getIdAgenda()));
    }

    @Test
    void shouldReturnTrueForOpenVoting() {
        VotingSession voting = getVotingSessionMock();
        boolean isOpen = votingSessionService.isOpen(voting);
        assertThat(isOpen, equalTo(true));
    }

    @Test
    void shouldReturnFalseForClosedVoting() {
        VotingSession voting = getVotingSessionMock();
        voting.setEndDate(LocalDateTime.now());
        boolean isOpen = votingSessionService.isOpen(voting);
        assertThat(isOpen, equalTo(false));
    }

    private VotingSession getVotingSessionMock() {
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
        return Agenda.builder()
                .id("6404ff797f24ce45b0022c83")
                .title("teste")
                .description("teste")
                .createdDate(LocalDateTime.now())
                .build();
    }

    private VotingSessionCreateDTO getVotingSessionCreateDTOMock() {
        return VotingSessionCreateDTO.builder()
                .idAgenda(getAgendaMock().getId())
                .duration(6000l)
                .build();
    }

    private VotingSessionDTO getVotingSessionDTOMock() {
        VotingSession votingSession = getVotingSessionMock();
        return VotingSessionDTO.builder()
                .id(votingSession.getId())
                .idAgenda(votingSession.getIdAgenda())
                .startDate(votingSession.getStartDate())
                .endDate(votingSession.getEndDate())
                .duration(votingSession.getDuration())
                .build();
    }

}
