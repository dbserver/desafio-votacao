package com.db.polling.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.db.polling.api.dto.VoteDTO;
import com.db.polling.api.dto.response.VoteResponse;
import com.db.polling.database.entity.AssociateEntity;
import com.db.polling.database.entity.VoteEntity;
import com.db.polling.database.entity.VotingSessionEntity;
import com.db.polling.database.repository.AssociateRepository;
import com.db.polling.database.repository.VoteRepository;
import com.db.polling.database.repository.VotingSessionRepository;
import com.db.polling.domain.enumeration.VoteEnum;
import com.db.polling.domain.exception.NotFoundException;
import com.db.polling.domain.exception.UnprocessableException;
import com.db.polling.domain.mapper.VoteMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VoteServiceImplTest {

  @InjectMocks
  private VoteServiceImpl service;

  @Mock
  private VoteRepository voteRepository;

  @Mock
  private VotingSessionRepository votingSessionRepository;

  @Mock
  private AssociateRepository associateRepository;

  @Mock
  private VoteMapper voteMapper;

  @Test
  public void shouldVoteSuccess() {
    VoteDTO voteDTO = buildVoteDTO();
    VoteEntity voteEntity = new VoteEntity();
    voteEntity.setVote(VoteEnum.YES);
    VotingSessionEntity votingSession = new VotingSessionEntity();
    VoteResponse voteResponse = new VoteResponse();
    voteResponse.setVote(VoteEnum.YES);
    AssociateEntity associateEntity = new AssociateEntity();

    when(votingSessionRepository.findById(voteDTO.getVotingSessionId())).thenReturn(
        Optional.of(votingSession));
    when(associateRepository.findById(voteDTO.getAssociateId())).thenReturn(
        Optional.of(associateEntity));
    when(voteRepository.existsByVotingSessionIdAndAssociateId(voteDTO.getVotingSessionId(),
        voteDTO.getAssociateId())).thenReturn(false);
    when(voteRepository.save(any())).thenReturn(any());

    VoteResponse result = service.vote(voteDTO.getAssociateId(), voteDTO);

    assertEquals(VoteEnum.YES, result.getVote());
  }

  @Test
  public void shouldVoteWithInvalidAssociateId() {
    VoteDTO voteDTO = buildVoteDTO();

    UnprocessableException exception = assertThrows(UnprocessableException.class, () -> {
      service.vote(100L, voteDTO);
    });

    assertEquals(exception.getCode(), "VOTE-007");
  }

  @Test
  public void shouldVoteWithNonExistingVotingSession() {
    VoteDTO voteDTO = buildVoteDTO();

    when(votingSessionRepository.findById(any()))
        .thenReturn(Optional.empty());

    NotFoundException exception = assertThrows(NotFoundException.class, () -> {
      service.vote(voteDTO.getAssociateId(), voteDTO);
    });

    assertEquals(exception.getCode(), "VOTE-005");
  }

  @Test
  public void shouldVoteWithClosedVotingSession() {
    VoteDTO voteDTO = buildVoteDTO();
    VotingSessionEntity votingSession = new VotingSessionEntity();
    votingSession.setClosingTime(LocalDateTime.now().minusHours(1));
    AssociateEntity associateEntity = new AssociateEntity();

    when(associateRepository.findById(voteDTO.getAssociateId())).thenReturn(
        Optional.of(associateEntity));
    when(votingSessionRepository.findById(voteDTO.getVotingSessionId()))
        .thenReturn(Optional.of(votingSession));

    UnprocessableException exception = assertThrows(UnprocessableException.class, () -> {
      service.vote(voteDTO.getAssociateId(), voteDTO);
    });

    assertEquals(exception.getCode(), "VOTE-008");
  }

  @Test
  public void shouldVoteWithAlreadyVoted() {

    VoteDTO voteDTO = new VoteDTO();
    voteDTO.setVotingSessionId(1L);
    voteDTO.setAssociateId(1L);
    voteDTO.setVote(VoteEnum.YES);

    VotingSessionEntity votingSessionEntity = new VotingSessionEntity();
    AssociateEntity associateEntity = new AssociateEntity();

    VoteEntity existingVote = new VoteEntity();
    existingVote.setVote(VoteEnum.YES);

    when(votingSessionRepository.findById(voteDTO.getVotingSessionId())).thenReturn(
        Optional.of(votingSessionEntity));
    when(associateRepository.findById(voteDTO.getAssociateId())).thenReturn(
        Optional.of(associateEntity));
    when(voteRepository.existsByVotingSessionIdAndAssociateId(voteDTO.getVotingSessionId(),
        voteDTO.getAssociateId())).thenReturn(true);

    UnprocessableException exception = assertThrows(UnprocessableException.class, () -> {
      service.vote(voteDTO.getAssociateId(), voteDTO);
    });

    assertEquals(exception.getCode(), "VOTE-009");
  }

  private VoteDTO buildVoteDTO() {
    VoteDTO voteDTO = new VoteDTO();
    voteDTO.setAssociateId(1L);
    voteDTO.setVotingSessionId(2L);
    voteDTO.setVote(VoteEnum.YES);

    return voteDTO;
  }
}
