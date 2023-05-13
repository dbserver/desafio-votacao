package com.db.polling.domain.service;

import com.db.polling.api.dto.VotingResultDTO;
import com.db.polling.api.dto.VotingSessionDTO;
import com.db.polling.api.dto.response.VoteResultResponse;

public interface VotingSessionService {

  VotingSessionDTO createVotingSession(VotingSessionDTO createVotingSessionDto);

  VotingSessionDTO getVotingSession(Long votingSessionId);

  VoteResultResponse getResult(Long votingSessionId);
}
