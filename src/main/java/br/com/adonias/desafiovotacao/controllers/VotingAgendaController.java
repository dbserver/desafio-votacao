package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.VotingAgendaBusiness;
import br.com.adonias.desafiovotacao.dto.VotingAgendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<VotingAgendaDTO>> getVotingAgendas(){
        return business.getAllVotingAgendas();
    }

    @PostMapping
    public ResponseEntity<VotingAgendaDTO> create(@RequestBody VotingAgendaDTO votingAgenda){
        return business.save(votingAgenda);
    }

    @PutMapping
    public ResponseEntity<VotingAgendaDTO> update(@RequestBody VotingAgendaDTO votingAgenda){
        return business.save(votingAgenda);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        business.delete(id);
    }

}
