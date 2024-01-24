package com.db.desafiovotacao.api.controller;

import com.db.desafiovotacao.api.entity.Agenda;
import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.record.CreateAgendaResponseRecord;
import com.db.desafiovotacao.api.service.CountVotesServiceInterface;
import com.db.desafiovotacao.api.service.CreateAgendaServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "1-Agenda", description = "Agenda management APIs")
@RestController
@RequestMapping("/agendas")
public class AgendaController {
    private final CountVotesServiceInterface countVotesService;

    private final CreateAgendaServiceInterface createAgendaService;

    @Autowired
    public AgendaController(CountVotesServiceInterface countVotesService, CreateAgendaServiceInterface createAgendaService) {
        this.countVotesService = countVotesService;
        this.createAgendaService = createAgendaService;
    }

    @Operation(summary = "Create an agenda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agenda created with success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) })})
    @PostMapping
    public ResponseEntity<CreateAgendaResponseRecord> createAgenda(@Parameter(description = "Agenda's name")@RequestParam(required = true)String name) {
        Agenda agenda = createAgendaService.createAgenda(name);
        return ResponseEntity.ok(new CreateAgendaResponseRecord(agenda.getId(),agenda.getName()));
    }

    @Operation(summary = "Open a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session opened with success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", description = "Agenda not found",
                    content = @Content) })
    @GetMapping("/{agendaId}/result")
    public ResponseEntity<String> getResultVoting(@PathVariable UUID agendaId) {
        try {
            String result = countVotesService.getResultVoting(agendaId);
            return ResponseEntity.ok(result);
        } catch (AgendaNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
