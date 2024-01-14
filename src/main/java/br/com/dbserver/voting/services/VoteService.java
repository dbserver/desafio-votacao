package br.com.dbserver.voting.services;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.vote.VoteRequestDTO;
import br.com.dbserver.voting.dtos.vote.VoteResponseDTO;

import java.util.List;

public interface VoteService {
    VoteResponseDTO voting(VoteRequestDTO voteRequestDTO);

    List<ResultOfTheVoteDTO> listVoteInProgress();
}
