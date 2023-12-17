package com.challenge.votation.controller;

import com.challenge.votation.model.*;
import com.challenge.votation.model.Error;
import com.challenge.votation.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/api/v1/documents", produces = {"application/json"})
@Tag(name = "documents")
public class DocumentController {

    private final DocumentService documentService;

    @Operation(summary = "Validate CPF document", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = DocumentResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            }),
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
            })
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DocumentResponse> verifyCpfDocument(@RequestBody @Valid DocumentRequest documentRequest) {
        log.info("Checking Cpf Document: {}", documentRequest);
        DocumentResponse documentResponse = documentService.verifyCpfDocument(documentRequest);

        return new ResponseEntity<>(documentResponse, HttpStatus.OK);
    }
}
