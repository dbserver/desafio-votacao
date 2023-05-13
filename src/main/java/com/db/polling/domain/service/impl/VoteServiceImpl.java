package com.db.polling.domain.service.impl;

import com.db.polling.api.dto.VoteDTO;
import com.db.polling.api.dto.response.VoteResponse;
import com.db.polling.database.entity.AssociateEntity;
import com.db.polling.database.entity.VoteEntity;
import com.db.polling.database.entity.VotingSessionEntity;
import com.db.polling.database.repository.AgendaRepository;
import com.db.polling.database.repository.AssociateRepository;
import com.db.polling.database.repository.VoteRepository;
import com.db.polling.database.repository.VotingSessionRepository;
import com.db.polling.domain.enumeration.VoteEnum;
import com.db.polling.domain.exception.NotFoundException;
import com.db.polling.domain.exception.UnprocessableException;
import com.db.polling.domain.mapper.VoteMapper;
import com.db.polling.domain.service.AgendaService;
import com.db.polling.domain.service.AssociateService;
import com.db.polling.domain.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteServiceImpl implements VoteService {

  @Autowired
  private VoteRepository repository;

  @Autowired
  private VotingSessionRepository votingSessionRepository;

  @Autowired
  private VoteMapper mapper;

  @Autowired
  private AssociateRepository associateRepository;

  @Override
  @Transactional
  public VoteResponse vote(Long associateId, VoteDTO voteDTO) {

    if (!voteDTO.getAssociateId().equals(associateId)) {
      throw new UnprocessableException("VOTE-007");
    }

    VotingSessionEntity votingSession = votingSessionRepository.findById(
            voteDTO.getVotingSessionId())
        .orElseThrow(() -> new NotFoundException("VOTE-005"));

    AssociateEntity associateEntity = associateRepository.findById(associateId)
        .orElseThrow(() -> new NotFoundException("VOTE-001"));

    if (votingSession.isClosed()) {
      throw new UnprocessableException("VOTE-008");
    }

    boolean hasVoted = repository.
        existsByVotingSessionIdAndAssociateId(voteDTO.getVotingSessionId(),
            voteDTO.getAssociateId());

    if (hasVoted) {
      throw new UnprocessableException("VOTE-009");
    }

    VoteEntity vote = new VoteEntity();
    vote.setVote(voteDTO.getVote());
    vote.setAssociateEntity(associateEntity);
    vote.setVotingSessionEntity(votingSession);
    repository.save(vote);

    return new VoteResponse(vote.getVote());
  }

}
