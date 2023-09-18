package com.desafio.projeto_votacao.controller;

import com.desafio.projeto_votacao.dto.VotoRequestDto;
import com.desafio.projeto_votacao.enums.VotoEnum;
import com.desafio.projeto_votacao.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/v1/votos")
@Tag(name = "Votos", description = "API para gerenciar votos")
public class VotoController {

    private final VotoService votoService;

    @PostMapping("/votar")
    @Operation(summary = "Registrar Voto", description = "Endpoint para registrar um novo voto.")
    @ApiResponse(responseCode = "201", description = "Voto registrado com sucesso.")
    @ApiResponse(responseCode = "400", description = "CPF não pode ser vazio ou nulo.")
    @ApiResponse(responseCode = "400", description = "CPF inválido.")
    @ApiResponse(responseCode = "409", description = "Associado já votou nessa pauta.")
    @ApiResponse(responseCode = "404", description = "Não existe uma sessão aberta.")
    @ApiResponse(responseCode = "404", description = "Associado não cadastrado para efetuar a votação.")
    @ApiResponse(responseCode = "500", description = "Erro interno.")
    public ResponseEntity<String> votar(
            @RequestParam VotoEnum voto,
            @RequestBody @Valid VotoRequestDto votoRequestDto) {
        votoService.registrarVoto(voto, votoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Voto registrado com sucesso.");
    }
}
