package com.challenge.service;

import com.challenge.dto.VoteRequestDto;
import com.challenge.model.Vote;

public interface VoteService {
    Vote save(VoteRequestDto request);
}
