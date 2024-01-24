package com.db.desafiovotacao.api.controller;

import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.exception.MemberNotFoundException;
import com.db.desafiovotacao.api.exception.OperationNotPermittedException;
import com.db.desafiovotacao.api.exception.SessionNotFoundException;
import com.db.desafiovotacao.api.record.VoteRecord;
import com.db.desafiovotacao.api.service.VoteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteServiceInterface voteService;

    @PostMapping
    public ResponseEntity<VoteRecord> votar(@RequestParam UUID memberId, @RequestParam UUID agendaId, @RequestParam Boolean voted) throws AgendaNotFoundException, MemberNotFoundException, OperationNotPermittedException {
        try {
            VoteRecord voteRecorded = voteService.vote(memberId, agendaId, voted);
            return ResponseEntity.ok(voteRecorded);
        } catch (AgendaNotFoundException | MemberNotFoundException | OperationNotPermittedException e) {
            throw e;
        }
    }

    @ExceptionHandler(AgendaNotFoundException.class)
    public ResponseEntity<String> handleSessionNotFoundException(AgendaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleSessionNotFoundException(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<String> handleSessionNotFoundException(OperationNotPermittedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
    }
}
