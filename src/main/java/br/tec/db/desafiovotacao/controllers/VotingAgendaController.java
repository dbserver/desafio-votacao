package br.tec.db.desafiovotacao.controllers;

import br.tec.db.desafiovotacao.business.VotingAgendaBusiness;
import br.tec.db.desafiovotacao.dto.VotingAgendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agendas")
public class VotingAgendaController {

    @Autowired
    private VotingAgendaBusiness business;

    @GetMapping("{id}")
    public ResponseEntity<VotingAgendaDTO> getVotingAgendaById(@PathVariable Long id){
        return business.getVotingAgendaById(id);
    }

    @GetMapping
    public ResponseEntity<VotingAgendaDTO> getVotingAgendas(){
        return business.getAllVotingAgendas();
    }

    @PostMapping
    public ResponseEntity<VotingAgendaDTO> create(@RequestBody VotingAgendaDTO votingAgendaDTO){
        return business.create(votingAgendaDTO);
    }

    @PutMapping
    public ResponseEntity<VotingAgendaDTO> update(@RequestBody VotingAgendaDTO votingAgendaDTO){
        return business.update(votingAgendaDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        business.delete(id);
    }

}
