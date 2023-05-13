package com.db.polling.domain.service;

import com.db.polling.api.dto.VoteDTO;
import com.db.polling.api.dto.response.VoteResponse;

public interface VoteService {

  VoteResponse vote(Long associateId, VoteDTO voteDTO);
}
