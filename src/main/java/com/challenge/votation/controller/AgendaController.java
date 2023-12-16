package com.challenge.votation.controller;


import com.challenge.votation.model.*;
import com.challenge.votation.model.Error;
import com.challenge.votation.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/api/v1/agendas", produces = {"application/json"})
@Tag(name = "agendas")
public class AgendaController {

    private final AgendaService agendaService;

    @Operation(summary = "Create Agenda", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaCreateResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AgendaCreateResponse> createAgenda(@RequestBody @Valid AgendaCreateRequest agendaCreateRequest) {
        log.info("Creating Agenda: {}", agendaCreateRequest);
        AgendaCreateResponse agendaCreateResponse = agendaService.createAgenda(agendaCreateRequest);

        return new ResponseEntity<>(agendaCreateResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Open Agenda", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaOpenResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
    })
    @PostMapping("/{agendaId}")
    public ResponseEntity<AgendaOpenResponse> openAgenda(@Parameter(name = "agendaId", description = "Agenda ID to be opened", required = true) @PathVariable Long agendaId,
                                                         @RequestBody AgendaOpenRequest agendaOpenRequest) {
        log.info("Opening Agenda Id: {}, with these information: {}", agendaId, agendaOpenRequest);
        AgendaOpenResponse agendaOpenResponse = agendaService.openAgenda(agendaId, agendaOpenRequest);

        return new ResponseEntity<>(agendaOpenResponse, HttpStatus.OK);
    }

    @Operation(summary = "Vote Agenda", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
    })
    @PostMapping("/{agendaId}/votes")
    public ResponseEntity<Void> vote(@Parameter(name = "agendaId", description = "Agenda ID to be voted", required = true) @PathVariable Long agendaId,
                                     @RequestBody @Valid AgendaVoteRequest agendaVoteRequest) {
        log.info("Voting Agenda Id: {}, with these information: {}", agendaId, agendaVoteRequest);
        agendaService.vote(agendaId, agendaVoteRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get Agenda Status", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
    })
    @GetMapping("/{agendaId}")
    public ResponseEntity<AgendaResponse> getAgendaStatus(@Parameter(name = "agendaId", description = "Agenda ID to get status", required = true) @PathVariable Long agendaId) {
        log.info("Getting Status Agenda Id: {}", agendaId);
        AgendaResponse agendaResponse = agendaService.getAgendaStatus(agendaId);

        return new ResponseEntity<>(agendaResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get a List of all Agendas Status", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AgendaResponsePage.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
    })
    @GetMapping
    public ResponseEntity<Page<AgendaResponse>> getAgendasStatus(@ParameterObject Pageable pageable) {
        log.info("Getting Status Agendas");
        Page<AgendaResponse> agendaResponses = agendaService.getAgendasStatus(pageable);

        return new ResponseEntity<>(agendaResponses, HttpStatus.OK);
    }
}
