package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.VoteBusiness;
import br.com.adonias.desafiovotacao.dto.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("votes")
public class VoteController {
    @Autowired
    private VoteBusiness business;

    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAllVotes(){
        return business.getAllVotes();
    }

    @GetMapping("{id}")
    public ResponseEntity<VoteDTO> getVote(@PathVariable("id") Long id){
        return business.getVoteById(id);
    }

    @GetMapping("agenda/{agendaId}")
    public ResponseEntity<List<VoteDTO>> getVotesByAgendaId(@PathVariable("agendaId") Long agendaId){
        return business.getAllVotesByAgendaId(agendaId);
    }

    @PostMapping
    public ResponseEntity<VoteDTO> create(@RequestBody VoteDTO vote){
        return business.create(vote);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        business.delete(id);
    }

    @PutMapping
    public ResponseEntity<VoteDTO> update(@RequestBody VoteDTO vote){
        return business.update(vote);
    }
}
