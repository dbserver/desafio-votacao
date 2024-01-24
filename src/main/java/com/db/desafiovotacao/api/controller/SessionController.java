package com.db.desafiovotacao.api.controller;

import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.exception.SessionNotFoundException;
import com.db.desafiovotacao.api.record.SessionRecord;
import com.db.desafiovotacao.api.record.VoteRecord;
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
                            schema = @Schema(implementation = SessionRecord.class)) }),
            @ApiResponse(responseCode = "405", description = "Agenda not found",
                    content = @Content) })
    @PostMapping("/{agendaId}")
    public ResponseEntity<SessionRecord> openSession(@Parameter(description = "id of agenda to be searched")@PathVariable UUID agendaId,
                                                     @Parameter(description = "duration of session")@RequestParam(required = false) Duration duration) throws AgendaNotFoundException {
        try {
            SessionRecord sessionRecored = openSessionService.openSession(agendaId, duration);
            return ResponseEntity.ok(sessionRecored);
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
