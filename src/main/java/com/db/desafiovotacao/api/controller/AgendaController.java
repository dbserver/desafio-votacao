package com.db.desafiovotacao.api.controller;

import com.db.desafiovotacao.api.record.AgendaRecord;
import com.db.desafiovotacao.api.service.CountVotesServiceInterface;
import com.db.desafiovotacao.api.service.CreateAgendaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    private final CountVotesServiceInterface countVotesService;

    private final CreateAgendaServiceInterface createAgendaService;

    @Autowired
    public AgendaController(CountVotesServiceInterface countVotesService, CreateAgendaServiceInterface createAgendaService) {
        this.countVotesService = countVotesService;
        this.createAgendaService = createAgendaService;
    }

    @PostMapping
    public ResponseEntity<AgendaRecord> createAgenda(@RequestBody AgendaRecord agendaRecord) {
        AgendaRecord agendaRecorded = createAgendaService.save(agendaRecord);
        return ResponseEntity.ok(agendaRecorded);
    }

    @GetMapping("/{agendaId}/result")
    public ResponseEntity<String> getResultVoting(@PathVariable UUID agendaId) {
        try {
            String result = countVotesService.getResultVoting(agendaId);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
