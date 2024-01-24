package com.db.desafiovotacao.api.controller;

import com.db.desafiovotacao.api.exception.AgendaNotFoundException;
import com.db.desafiovotacao.api.exception.MemberNotFoundException;
import com.db.desafiovotacao.api.exception.OperationNotPermittedException;
import com.db.desafiovotacao.api.exception.SessionNotFoundException;
import com.db.desafiovotacao.api.record.VoteRecord;
import com.db.desafiovotacao.api.service.VoteServiceInterface;
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

import java.util.UUID;

@Tag(name = "3-Vote", description = "Vote management APIs")
@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteServiceInterface voteService;

    @Operation(summary = "Vote in a agenda for a session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voted with success",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = VoteRecord.class)) }),
            @ApiResponse(responseCode = "405", description = "Operation not permitted",
                    content = @Content),
            @ApiResponse(responseCode = "405", description = "Agenda or Member not found",
                    content = @Content) })
    @PostMapping
    public ResponseEntity<VoteRecord> vote(@Parameter(description = "id of member to be searched")@RequestParam UUID memberId,
                                           @Parameter(description = "id of agenda to be searched")@RequestParam UUID agendaId,
                                           @Parameter(description = "vote flag")@RequestParam Boolean voted) throws AgendaNotFoundException, MemberNotFoundException, OperationNotPermittedException {
        try {
            VoteRecord voteRecorded = voteService.vote(memberId, agendaId, voted);
            return ResponseEntity.ok(voteRecorded);
        } catch (AgendaNotFoundException | MemberNotFoundException | OperationNotPermittedException e) {
            throw e;
        }
    }

    @ExceptionHandler(AgendaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleSessionNotFoundException(AgendaNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleSessionNotFoundException(MemberNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<String> handleOperationNotPermittedException(OperationNotPermittedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
