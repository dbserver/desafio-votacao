package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.ComputingVoteDTO;
import com.example.desafiovotacao.dto.VotedDTO;
import com.example.desafiovotacao.service.interfaces.VoteInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Votos", description = "Cadastre o voto de um associado")
@RestController
@RequestMapping("/vote")
@AllArgsConstructor
public class VoteController {
    private final VoteInterface voteService;

    @Operation(
            summary = "Computar um novo voto do associado",
            description = "Cadastra o voto Sim/Não de um associado na sessão ativa referente à pauta desejada"
    )
    @PostMapping("/compute")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = VotedDTO.class))
    )
    public ResponseEntity<VotedDTO> compute(@RequestBody ComputingVoteDTO vote){
        return ResponseEntity.status(HttpStatus.OK).body(voteService.create(vote));
    }

}
