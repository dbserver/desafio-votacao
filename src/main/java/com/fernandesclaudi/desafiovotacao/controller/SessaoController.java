package com.fernandesclaudi.desafiovotacao.controller;

import com.fernandesclaudi.desafiovotacao.config.VersionApi;
import com.fernandesclaudi.desafiovotacao.dto.ContabilizacaoDto;
import com.fernandesclaudi.desafiovotacao.dto.SessaoDto;
import com.fernandesclaudi.desafiovotacao.dto.VotoDto;
import com.fernandesclaudi.desafiovotacao.model.Sessao;
import com.fernandesclaudi.desafiovotacao.model.Voto;
import com.fernandesclaudi.desafiovotacao.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sessão", description = "Sessão API")
@RestController
@RequestMapping(VersionApi.VERSION + "/sessao")
public class SessaoController {
    @Autowired
    private SessaoService sessaoService;

    @Operation(
            summary = "Abrir sessão",
            description = "Abre uma sessão para votação e retorna sua entidade"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sessão aberta com sucesso"),
            @ApiResponse(responseCode = "404", description = "Registro de pauta não encontrado"),
            @ApiResponse(responseCode = "422", description = "Sessão aberta encontrada para a pauta informada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("abrir")
    private ResponseEntity<Sessao> abrir(@Valid @RequestBody SessaoDto sessaoDto) {
        return ResponseEntity.ok(sessaoService.abrir(sessaoDto));
    }

    @Operation(
            summary = "Sessões abertas",
            description = "Retorna uma lista com as sessões abertas"
    )
    @ApiResponse(responseCode = "200", description = "Lista de sessões abertas")
    @GetMapping( "abertas")
    private ResponseEntity<List<Sessao>> abertas() {
        return ResponseEntity.ok(sessaoService.sessoesAbertas());
    }

    @Operation(
            summary = "Votar",
            description = "Registra voto em uma sessão"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Voto registrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Associado ou sessão não encontrado"),
            @ApiResponse(responseCode = "422", description = "Sessão já encerrada ou Associado já realizou sua votação para essa sessão"),
    })
    @PostMapping("votar")
    private ResponseEntity<Voto> votar(@RequestBody @Valid VotoDto votoDto) {
        return ResponseEntity.ok(sessaoService.registrarVoto(votoDto));
    }

    @Operation(
            summary = "Contabilizar votos",
            description = "Contabiliza os votos de uma sessão e retorna o resultado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Resultado da votação contabilizada."),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada ou nenhum voto realizado para a sessão."),
    })
    @GetMapping("contabilizar/{idSessao}")
    private ResponseEntity<ContabilizacaoDto> contabilizar(
            @PathVariable
            @Valid
            @Min(value = 1, message = "Id da sessão deve ser maior ou igual a um") Long idSessao) {
        return ResponseEntity.ok(sessaoService.contabilizarVotos(idSessao));
    }

}