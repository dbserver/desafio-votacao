package com.dbserver.controller;

import com.dbserver.model.dto.VotingSessionCreateDTO;
import com.dbserver.model.dto.VotingSessionDTO;
import com.dbserver.service.VotingSessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/voting-session")
public class VotingSessionController {

    @Autowired
    private VotingSessionService votingSessionService;

    @PostMapping
    public ResponseEntity<VotingSessionDTO> create(@Valid @RequestBody VotingSessionCreateDTO agendaOpenVotingDTO) {
        VotingSessionDTO voting = votingSessionService.create(agendaOpenVotingDTO);
        return new ResponseEntity<>(voting, CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotingSessionDTO> getById(@PathVariable String id) {
        VotingSessionDTO voting = votingSessionService.getById(id);
        return new ResponseEntity<>(voting, OK);
    }

}
