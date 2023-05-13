package com.db.polling.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.db.polling.api.dto.VotingSessionDTO;
import com.db.polling.api.dto.response.VoteResultResponse;
import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.entity.VotingSessionEntity;
import com.db.polling.database.repository.AgendaRepository;
import com.db.polling.database.repository.VoteRepository;
import com.db.polling.database.repository.VotingSessionRepository;
import com.db.polling.domain.enumeration.VoteEnum;
import com.db.polling.domain.exception.UnprocessableException;
import com.db.polling.domain.mapper.VotingSessionMapper;
import com.db.polling.domain.service.validators.ValidateExistOpenPolling;
import com.db.polling.domain.service.validators.ValidateTimeSession;
import com.db.polling.domain.service.validators.VoteSessionValidation;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class VotingSessionServiceImplTest {

  @Mock
  private VotingSessionMapper mapper;

  @Mock
  private VotingSessionRepository repository;

  @Mock
  private VoteRepository voteRepository;

  @Mock
  private AgendaRepository agendaRepository;

  @Mock
  private List<VoteSessionValidation> validation;

  @Mock
  private ValidateTimeSession validateTimeSession;

  @Mock
  private ValidateExistOpenPolling validateExistOpenPolling;

  @InjectMocks
  private VotingSessionServiceImpl service;


  @BeforeEach
  void before() {
    ReflectionTestUtils.setField(service, "validation",
        List.of(validateTimeSession, validateExistOpenPolling));
  }


  @Test
  void shouldCreateVotingSession() {
    VotingSessionDTO input = new VotingSessionDTO();
    input.setAgendaId(1L);
    VotingSessionEntity votingSession = new VotingSessionEntity();

    AgendaEntity agendaEntity = new AgendaEntity();
    when(agendaRepository.findById(input.getAgendaId())).thenReturn(Optional.of(agendaEntity));
    when(repository.save(votingSession)).thenReturn(votingSession);
    when(mapper.toDTO(votingSession)).thenReturn(input);
    when(mapper.toEntity(input)).thenReturn(votingSession);

    VotingSessionDTO response = service.createVotingSession(input);

    assertEquals(input.getAgendaId(), response.getAgendaId());
    verify(agendaRepository).findById(input.getAgendaId());
    verify(repository).save(votingSession);
    verify(mapper).toDTO(votingSession);
    verify(mapper).toEntity(input);
  }

  @Test
  public void shouldGetResult() {
    Long yesVotes = 3L;
    Long noVotes = 2L;

    VotingSessionEntity votingSession = new VotingSessionEntity();
    votingSession.setVotingSessionId(1L);
    votingSession.setClosingTime(LocalDateTime.now().minusMinutes(10));

    when(repository.findById(1L)).thenReturn(Optional.of(votingSession));
    when(voteRepository.countByVotingSessionIdAndVote(1L, VoteEnum.YES)).thenReturn(
        yesVotes);
    when(voteRepository.countByVotingSessionIdAndVote(1L, VoteEnum.NO)).thenReturn(noVotes);

    VoteResultResponse result = service.getResult(1L);

    assertEquals(yesVotes, result.getYesVotes());
    assertEquals(noVotes, result.getNoVotes());
    assertEquals(yesVotes + noVotes, result.getTotalVotes());
  }

  @Test
  public void shouldGetResultThrowsExceptionWhenVotingSessionIsNotClosed() {

    VotingSessionEntity votingSession = new VotingSessionEntity();
    votingSession.setVotingSessionId(1L);
    votingSession.setClosingTime(LocalDateTime.now().plusMinutes(10));

    when(repository.findById(1L)).thenReturn(Optional.of(votingSession));

    UnprocessableException exception = assertThrows(UnprocessableException.class, () -> {
      service.getResult(votingSession.getVotingSessionId());
    });

    assertEquals(exception.getCode(), "VOTE-010");
  }

  @Test
  public void shouldGetVotingSession() {
    LocalDateTime open = LocalDateTime.now();
    LocalDateTime close = LocalDateTime.now().plusMinutes(10);

    VotingSessionEntity votingSession = new VotingSessionEntity();
    votingSession.setVotingSessionId(1L);
    votingSession.setOpeningTime(open);
    votingSession.setClosingTime(close);

    VotingSessionDTO votingSessionDTO = new VotingSessionDTO();
    votingSessionDTO.setOpeningTime(open);
    votingSessionDTO.setClosingTime(close);

    when(repository.findById(votingSession.getVotingSessionId()))
        .thenReturn(Optional.of(votingSession));
    when(mapper.toDTO(votingSession)).thenReturn(votingSessionDTO);

    VotingSessionDTO response = service.getVotingSession(votingSession.getVotingSessionId());

    assertEquals(response.getClosingTime(), votingSessionDTO.getClosingTime());
    assertEquals(response.getOpeningTime(), votingSessionDTO.getOpeningTime());

    verify(repository).findById(votingSession.getVotingSessionId());
    verify(mapper).toDTO(votingSession);
  }
}
