package br.tec.db.desafiovotacao.business;

import br.tec.db.desafiovotacao.dto.VoteDTO;
import br.tec.db.desafiovotacao.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VoteBusiness {

    @Autowired
    private VoteService voteService;

    public ResponseEntity<VoteDTO> getVoteById(Long id) {
        return null;
    }

    public ResponseEntity<VoteDTO> getVotes() {
        return null;
    }

    public ResponseEntity<VoteDTO> createOrUpdate(VoteDTO voteDTO) {
        return null;
    }

    public void delete(Long id) {
    }
}
