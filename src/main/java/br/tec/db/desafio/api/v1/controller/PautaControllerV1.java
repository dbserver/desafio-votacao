package br.tec.db.desafio.api.v1.controller;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.service.IPautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("api/v1/pauta")
@Tag(name = "Pauta")
public class PautaControllerV1 {
    private final IPautaService pautaService;

    public PautaControllerV1(IPautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de pauta")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description  = "Ok"),
            @ApiResponse(responseCode = "400", description = "Dados do payload inválidos."),
            @ApiResponse(responseCode = "422", description = "Dado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor. Por favor, tente novamente mais tarde.")
    })
    public ResponseEntity<PautaResponseV1> cadastraPauta(
            @NotEmpty(message = "A pauta é obrigatória e deve ser preenchida")
            @RequestBody @Valid PautaRequestV1 pautaRequestV1) {
            PautaResponseV1 pautaResponseV1 = pautaService.criarUmaNovaPauta(pautaRequestV1);
        return new ResponseEntity<>(pautaResponseV1, HttpStatus.CREATED);
}
}
