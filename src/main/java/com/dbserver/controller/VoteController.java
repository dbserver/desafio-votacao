package com.dbserver.controller;


import com.dbserver.model.dto.VoteCreatedDTO;
import com.dbserver.model.dto.VoteDTO;
import com.dbserver.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<VoteDTO> create(@Valid @RequestBody VoteCreatedDTO voteCreatedDTO) {
        VoteDTO vote = voteService.create(voteCreatedDTO);
        return new ResponseEntity<>(vote, CREATED);
    }

}
