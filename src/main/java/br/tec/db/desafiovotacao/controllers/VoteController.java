package br.tec.db.desafiovotacao.controllers;

import br.tec.db.desafiovotacao.business.VoteBusiness;
import br.tec.db.desafiovotacao.dto.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("votes")
public class VoteController {

    @Autowired
    private VoteBusiness business;

    @GetMapping("{id}")
    public ResponseEntity<VoteDTO> getVote(@PathVariable Long id){
        return business.getVoteById(id);
    }

    @GetMapping
    public ResponseEntity<VoteDTO> getAllVotes(){
        return business.getVotes();
    }

    @PostMapping
    public ResponseEntity<VoteDTO> create(@RequestBody VoteDTO voteDTO){
        return business.createOrUpdate(voteDTO);
    }

    @PutMapping
    public ResponseEntity<VoteDTO> update(@RequestBody VoteDTO voteDTO){
        return business.createOrUpdate(voteDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        business.delete(id);
    }
}
