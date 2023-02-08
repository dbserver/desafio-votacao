package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.VoteBusiness;
import br.com.adonias.desafiovotacao.dto.VoteDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("votes")
public class VoteController {
    @Autowired
    private VoteBusiness business;

    @ApiOperation("Returns all votes")
    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAllVotes(){
        return business.getAllVotes();
    }

    @ApiOperation("Return a vote by id")
    @GetMapping("{id}")
    public ResponseEntity<VoteDTO> getVoteById(@PathVariable("id") Long id){
        return business.getVoteById(id);
    }

    @ApiOperation("Returns a vote list by agenda id.")
    @GetMapping("agenda/{agendaId}")
    public ResponseEntity<List<VoteDTO>> getVotesByAgendaId(@PathVariable("agendaId") Long agendaId){
        return business.getAllVotesByAgendaId(agendaId);
    }

    @ApiOperation("Create a new vote")
    @PostMapping
    public ResponseEntity<VoteDTO> create(@RequestBody VoteDTO vote){
        return business.create(vote);
    }

    @ApiOperation("Delete a existing vote by id")
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        business.delete(id);
    }

    @ApiOperation("Update a vote")
    @PutMapping
    public ResponseEntity<VoteDTO> update(@RequestBody VoteDTO vote){
        return business.update(vote);
    }
}
