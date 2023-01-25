package br.tec.db.desafiovotacao.business;

import br.tec.db.desafiovotacao.dto.VotingAgendaDTO;
import br.tec.db.desafiovotacao.services.VotingAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VotingAgendaBusiness {

    @Autowired
    private VotingAgendaService votingAgendaService;

    public ResponseEntity<VotingAgendaDTO> getVotingAgendaById(Long id) {
        return null;
    }

    public ResponseEntity<VotingAgendaDTO> getAllVotingAgendas() {
        return null;
    }

    public ResponseEntity<VotingAgendaDTO> create(VotingAgendaDTO votingAgendaDTO) {
        return null;
    }

    public ResponseEntity<VotingAgendaDTO> update(VotingAgendaDTO votingAgendaDTO) {
        return null;
    }

    public void delete(Long id) {
    }
}
