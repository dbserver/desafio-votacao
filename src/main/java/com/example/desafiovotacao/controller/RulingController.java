package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.CountingResultsDTO;
import com.example.desafiovotacao.dto.CreatedRulingDTO;
import com.example.desafiovotacao.dto.RegisterRulingDTO;
import com.example.desafiovotacao.dto.RulingReturnDTO;
import com.example.desafiovotacao.service.implementations.RulingServiceImpl;
import com.example.desafiovotacao.service.interfaces.RulingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pautas", description = "Consulte e crie pautas ou contabilize os votos da última sessão")
@RestController
@RequestMapping("/ruling")
@RequiredArgsConstructor
public class RulingController {
    private final RulingServiceImpl rulingService;

    @Operation(
            summary = "Criar uma nova pauta",
            description = "Cria uma nova pauta para votação"
    )
    @PostMapping("/create")
    @ApiResponse(
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = CreatedRulingDTO.class))
    )
    public ResponseEntity<CreatedRulingDTO> save(@RequestBody RegisterRulingDTO ruling){
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(rulingService.create(ruling));
    }

    @Operation(
            summary = "Realizar contagem de votos",
            description = "Realiza a contagem de todos os votos da última sessão finalizada desta pauta"
    )
    @PostMapping("/countVotes/{rulingId}")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = CountingResultsDTO.class))
    )
    public ResponseEntity<CountingResultsDTO> countVotes(@PathVariable Integer rulingId){
        return ResponseEntity.status(HttpStatus.OK).body(rulingService.countVotes(rulingId));
    }

    @Operation(
            summary = "Listar todas as pautas",
            description = "Lista todas as pautas criadas"
    )
    @GetMapping("/list")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = List.class))
    )
    public ResponseEntity<List<RulingReturnDTO>> list() {
        return new ResponseEntity<>(rulingService.listAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Listar pauta com base no identificador",
            description = "Retorna um registro único de uma pauta existente com base no código identificador"
    )
    @GetMapping("/list/{id}")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = RulingReturnDTO.class))
    )
    public ResponseEntity<RulingReturnDTO> listById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(rulingService.getRulingReturnIfExists(id));
    }
}
