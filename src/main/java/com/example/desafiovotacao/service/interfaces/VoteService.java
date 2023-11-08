package com.example.desafiovotacao.service.interfaces;

import com.example.desafiovotacao.dto.ComputingVoteDTO;
import com.example.desafiovotacao.dto.VotedDTO;

public interface VoteService {
    VotedDTO create(ComputingVoteDTO vote);
}
