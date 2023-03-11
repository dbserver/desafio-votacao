package com.dbserver.service;

import com.dbserver.exception.BusinessException;
import com.dbserver.exception.ConflictException;
import com.dbserver.exception.EntityNotFoundException;
import com.dbserver.model.dto.VotingCreateDTO;
import com.dbserver.model.dto.VotingDTO;
import com.dbserver.model.entity.Agenda;
import com.dbserver.model.entity.Voting;
import com.dbserver.model.mapper.VotingMapper;
import com.dbserver.repository.VotingRepository;
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
class VotingServiceTest {

    @Mock
    private VotingRepository votingRepository;

    @Mock
    private VotingMapper votingMapper;

    @Mock
    private AgendaService agendaService;

    @InjectMocks
    private VotingService votingService;

    @Test
    void shouldCreateVoting() {
        Voting voting = getVotingMock();
        Agenda agenda = getAgendaMock();
        VotingCreateDTO votingCreateDTO = getVotingCreateDTOMock();
        VotingDTO votingDTO = getVotingDTOMock();
        when(agendaService.findById(any())).thenReturn(agenda);
        when(votingMapper.toEntity(votingCreateDTO)).thenReturn(voting);
        when(votingRepository.save(voting)).thenReturn(voting);
        when(votingMapper.toDTO(voting)).thenReturn(votingDTO);
        VotingDTO saved = votingService.create(votingCreateDTO);
        assertThat(saved, equalTo(votingDTO));
    }

    @Test
    void shouldThrowConflictException() {
        Voting voting = getVotingMock();
        Agenda agenda = getAgendaMock();
        VotingCreateDTO votingCreateDTO = getVotingCreateDTOMock();
        when(agendaService.findById(any())).thenReturn(agenda);
        when(votingMapper.toEntity(votingCreateDTO)).thenReturn(voting);
        when(votingRepository.save(voting)).thenThrow(DuplicateKeyException.class);
        ConflictException throwable =
                catchThrowableOfType(() -> votingService.create(votingCreateDTO), ConflictException.class);
        assertThat(throwable.getClass(), equalTo(ConflictException.class));
        assertThat(throwable.getReason(), equalTo("Voting already started"));
    }

    @Test
    void shouldThrowBusinessException() {
        Voting voting = getVotingMock();
        Agenda agenda = getAgendaMock();
        VotingCreateDTO votingCreateDTO = getVotingCreateDTOMock();
        when(agendaService.findById(any())).thenReturn(agenda);
        when(votingMapper.toEntity(votingCreateDTO)).thenReturn(voting);
        when(votingRepository.save(voting)).thenThrow(RuntimeException.class);
        BusinessException throwable =
                catchThrowableOfType(() -> votingService.create(votingCreateDTO), BusinessException.class);
        assertThat(throwable.getClass(), equalTo(BusinessException.class));
    }

    @Test
    void shouldGetVotingDTOById() {
        Voting voting = getVotingMock();
        VotingDTO votingDTO = getVotingDTOMock();
        votingDTO.setId(voting.getId());
        votingDTO.setIdAgenda(voting.getIdAgenda());
        when(votingRepository.findById(voting.getId())).thenReturn(Optional.of(voting));
        when(votingMapper.toDTO(voting)).thenReturn(votingDTO);
        VotingDTO found = votingService.getById(voting.getId());
        assertThat(found, equalTo(votingDTO));
    }

    @Test
    void shouldFindVotingById() {
        Voting voting = getVotingMock();
        when(votingRepository.findById(voting.getId())).thenReturn(Optional.of(voting));
        Voting found = votingService.findById(voting.getId());
        assertThat(found, equalTo(voting));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionOnFindById() {
        Voting voting = getVotingMock();
        when(votingRepository.findById(voting.getId())).thenReturn(Optional.empty());
        EntityNotFoundException throwable =
                catchThrowableOfType(() -> votingService.findById(voting.getId()), EntityNotFoundException.class);
        assertThat(throwable.getClass(), equalTo(EntityNotFoundException.class));
        assertThat(throwable.getReason(), equalTo("Voting not found: " + voting.getId()));
    }

    @Test
    void shouldFindVotingByIdAgenda() {
        Voting voting = getVotingMock();
        when(votingRepository.findByIdAgenda(voting.getIdAgenda())).thenReturn(Optional.of(voting));
        Voting found = votingService.findByIdAgenda(voting.getIdAgenda());
        assertThat(found, equalTo(voting));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionOnGetByIdAgenda() {
        Voting voting = getVotingMock();
        when(votingRepository.findByIdAgenda(voting.getIdAgenda())).thenReturn(Optional.empty());
        EntityNotFoundException throwable =
                catchThrowableOfType(() -> votingService.findByIdAgenda(voting.getIdAgenda()), EntityNotFoundException.class);
        assertThat(throwable.getClass(), equalTo(EntityNotFoundException.class));
        assertThat(throwable.getReason(), equalTo("Voting not found for idAgenda: " + voting.getIdAgenda()));
    }

    @Test
    void shouldReturnTrueForOpenVoting() {
        Voting voting = getVotingMock();
        boolean isOpen = votingService.isOpen(voting);
        assertThat(isOpen, equalTo(true));
    }

    @Test
    void shouldReturnFalseForClosedVoting() {
        Voting voting = getVotingMock();
        voting.setEndDate(LocalDateTime.now());
        boolean isOpen = votingService.isOpen(voting);
        assertThat(isOpen, equalTo(false));
    }

    private Voting getVotingMock() {
        long duration = 60000l;
        return Voting.builder()
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

    private VotingCreateDTO getVotingCreateDTOMock() {
        return VotingCreateDTO.builder().build();
    }

    private VotingDTO getVotingDTOMock() {
        return VotingDTO.builder().build();
    }

}
