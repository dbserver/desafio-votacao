package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.VoteBusiness;
import br.com.adonias.desafiovotacao.dto.VoteDTO;
import br.com.adonias.desafiovotacao.entities.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("votes")
public class VoteController {
    @Autowired
    private VoteBusiness business;

    @GetMapping("{id}")
    public ResponseEntity<VoteDTO> getVote(@PathVariable("id") Long id){
        return business.getVoteById(id);
    }

    @PostMapping
    public ResponseEntity<VoteDTO> toVote(@RequestBody VoteDTO vote){
        return business.save(vote);
    }

    @DeleteMapping("{id}")
    public void undoVote(@PathVariable Long id){
        business.delete(id);
    }

    @PutMapping
    public ResponseEntity<VoteDTO> update(@RequestBody VoteDTO vote){
        return business.save(vote);
    }
}
