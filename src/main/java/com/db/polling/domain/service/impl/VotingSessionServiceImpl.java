package com.db.polling.domain.service.impl;

import com.db.polling.api.dto.VotingSessionDTO;
import com.db.polling.api.dto.response.VoteResultResponse;
import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.entity.VotingSessionEntity;
import com.db.polling.database.repository.AgendaRepository;
import com.db.polling.database.repository.VoteRepository;
import com.db.polling.database.repository.VotingSessionRepository;
import com.db.polling.domain.enumeration.VoteEnum;
import com.db.polling.domain.exception.NotFoundException;
import com.db.polling.domain.exception.UnprocessableException;
import com.db.polling.domain.mapper.VotingSessionMapper;
import com.db.polling.domain.service.VotingSessionService;
import com.db.polling.domain.service.validators.VoteSessionValidation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotingSessionServiceImpl implements VotingSessionService {

  @Autowired
  private VotingSessionMapper mapper;

  @Autowired
  private VotingSessionRepository repository;

  @Autowired
  private VoteRepository voteRepository;

  @Autowired
  private AgendaRepository agendaRepository;

  @Autowired
  private List<VoteSessionValidation> validation;


  @Override
  @Transactional
  public VotingSessionDTO createVotingSession(VotingSessionDTO createVotingSessionDTO) {

    AgendaEntity agendaEntity = validateIfExistAgenda(createVotingSessionDTO.getAgendaId());

    validation.forEach(voteSessionValidation -> {
      voteSessionValidation.validate(agendaEntity);
    });

    VotingSessionEntity votingSessionEntity = mapper.toEntity(createVotingSessionDTO);
    votingSessionEntity.setAgendaEntity(agendaEntity);

    VotingSessionEntity votingSession = repository.save(votingSessionEntity);

    return mapper.toDTO(votingSession);
  }

  @Override
  public VotingSessionDTO getVotingSession(Long votingSessionId) {
    VotingSessionEntity votingSession = getVotingSessionById(votingSessionId);
    return mapper.toDTO(votingSession);
  }

  @Override
  public VoteResultResponse getResult(Long votingSessionId) {

    VotingSessionEntity votingSession = getVotingSessionById(votingSessionId);
    if (!votingSession.isClosed()) {
      throw new UnprocessableException("VOTE-010");
    }
    Long yesVotes = voteRepository.countByVotingSessionIdAndVote(votingSessionId,
        VoteEnum.YES);
    Long noVotes = voteRepository.countByVotingSessionIdAndVote(votingSessionId,
        VoteEnum.NO);

    return buildResponse(yesVotes, noVotes);
  }

  private AgendaEntity validateIfExistAgenda(Long agendaId) {
    return agendaRepository.findById(agendaId)
        .orElseThrow(() -> new NotFoundException("VOTE-003"));
  }

  private VotingSessionEntity getVotingSessionById(Long votingSessionId) {
    return repository.findById(votingSessionId)
        .orElseThrow(() -> new NotFoundException("VOTE-005"));
  }

  private VoteResultResponse buildResponse(Long yesVotes, Long noVotes) {
    VoteResultResponse response = new VoteResultResponse();
    response.setYesVotes(yesVotes);
    response.setNoVotes(noVotes);
    response.setTotalVotes(yesVotes + noVotes);

    return response;
  }
}
