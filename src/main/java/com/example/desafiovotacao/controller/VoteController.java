package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.ComputingVoteDTO;
import com.example.desafiovotacao.dto.VotedDTO;
import com.example.desafiovotacao.service.interfaces.VoteInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vote")
@AllArgsConstructor
public class VoteController {
    private final VoteInterface voteService;

    @PostMapping("/compute")
    public ResponseEntity<VotedDTO> compute(@RequestBody ComputingVoteDTO vote){
        return ResponseEntity.status(HttpStatus.OK).body(voteService.create(vote));
    }

}
