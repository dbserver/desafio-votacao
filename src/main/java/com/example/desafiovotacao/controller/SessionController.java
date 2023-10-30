package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.CreatedSessionDTO;
import com.example.desafiovotacao.dto.SessionReturnDTO;
import com.example.desafiovotacao.dto.StartSessionDTO;
import com.example.desafiovotacao.service.implementations.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sessão", description = "Consulte ou crie sessões")
@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @Operation(
            summary = "Criar uma nova sessão de votação",
            description = "Realiza a criação de uma nova sessão de votação para determinada pauta"
    )
    @PostMapping("/create")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = CreatedSessionDTO.class))
    )
    public ResponseEntity<CreatedSessionDTO> create(@RequestBody StartSessionDTO startSessionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.create(startSessionDTO));
    }

    @Operation(
            summary = "Listar todas as sessões",
            description = "Lista todas as sessões criadas independente de pauta"
    )
    @GetMapping("/list")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = List.class))
    )
    public ResponseEntity<List<SessionReturnDTO>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.listAll());
    }

    @Operation(
            summary = "Listar sessão por identificador",
            description = "Lista uma sessão específica de acordo com o identificador fornecido"
    )
    @GetMapping("/list/{sessionId}")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = SessionReturnDTO.class))
    )
    public ResponseEntity<SessionReturnDTO> findById(@PathVariable Integer sessionId) {
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.listById(sessionId));
    }

}
