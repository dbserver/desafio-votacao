package br.com.dbserver.voting.services;

import br.com.dbserver.voting.dtos.vote.ResultOfTheVoteDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionRequestDTO;
import br.com.dbserver.voting.dtos.votingsession.VotingSessionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VotingSessionService {
    VotingSessionResponseDTO openVoting(VotingSessionRequestDTO votingSessionRequestDTO);

    Page<VotingSessionResponseDTO> listAll(Pageable pageable);

    ResultOfTheVoteDTO closeVoting(String sessionId);
}
