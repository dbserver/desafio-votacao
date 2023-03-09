package com.dbserver.controller;

import com.dbserver.model.dto.VotingCreateDTO;
import com.dbserver.model.dto.VotingDTO;
import com.dbserver.service.VotingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/voting")
public class VotingController {

    @Autowired
    private VotingService votingService;

    @PostMapping
    public ResponseEntity<VotingDTO> create(@Valid @RequestBody VotingCreateDTO agendaOpenVotingDTO) {
        VotingDTO voting = votingService.create(agendaOpenVotingDTO);
        return new ResponseEntity<>(voting, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotingDTO> getById(@PathVariable String id) {
        VotingDTO voting = votingService.getById(id);
        return new ResponseEntity<>(voting, OK);
    }

}
