package com.db.desafiovotacao.api.controller;

import com.db.desafiovotacao.api.entity.Session;
import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.record.OpenSessionAgendaRecord;
import com.db.desafiovotacao.api.record.OpenSessionRecord;
import com.db.desafiovotacao.api.service.OpenSessionServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.UUID;

@Tag(name = "2-Session", description = "Session management APIs")
@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private OpenSessionServiceInterface openSessionService;

    @Operation(summary = "Open a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session opened with success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OpenSessionRecord.class)) }),
            @ApiResponse(responseCode = "405", description = "Agenda not found",
                    content = @Content) })
    @PostMapping("/{agendaId}")
    public ResponseEntity<OpenSessionRecord> openSession(@Parameter(description = "id of agenda to be searched")@PathVariable("agendaId") UUID agendaId,
                                                         @Parameter(description = "duration of session")@RequestParam(required = true) Duration duration) throws AgendaNotFoundException {
        try {
            Session session = openSessionService.openSession(agendaId, duration);
            return ResponseEntity.ok(new OpenSessionRecord(session.getId(), session.getDataBegin(), session.getDataEnd(), new OpenSessionAgendaRecord(session.getAgenda().getId(),session.getAgenda().getName(),session.getAgenda().getVotes()), session.getStatus()));
        } catch (AgendaNotFoundException e) {
            throw e;
        }
    }

    @ExceptionHandler(AgendaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleAgendaNotFoundException(AgendaNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
