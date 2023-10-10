package com.challenge.service;

import com.challenge.dto.StaveRequestDto;
import com.challenge.dto.StaveSessionRequestDto;
import com.challenge.dto.VoteCountResponseDto;
import com.challenge.model.Stave;
import com.challenge.model.StaveSession;

public interface StaveService {
    Stave create(StaveRequestDto request);

    StaveSession session(Long staveId, StaveSessionRequestDto request);

    VoteCountResponseDto countVotes(Long staveId);
}
