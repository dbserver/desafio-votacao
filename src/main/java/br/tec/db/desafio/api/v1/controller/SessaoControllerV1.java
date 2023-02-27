package br.tec.db.desafio.api.v1.controller;

import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaCriarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaSaberTotalVotosRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.request.SessaoParaVotarRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoCriadaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoTotalVotosResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.response.SessaoVotadaResponseV1;
import br.tec.db.desafio.business.service.ISessaoService;
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
@RequestMapping("api/v1/sessao")
@Tag(name = "Sessao")
public class SessaoControllerV1 {
    private final ISessaoService sessaoService;

    public SessaoControllerV1(ISessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de sessão")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description  = "Ok"),
            @ApiResponse(responseCode = "400", description = "Dados do payload inválidos."),
            @ApiResponse(responseCode = "422", description = "Dado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor. Por favor, tente novamente mais tarde.")
    })
    public ResponseEntity<SessaoCriadaResponseV1> criarUmaNovaSessao(
            @NotEmpty(message = "A sessão é obrigatória e deve ser preenchida")
            @RequestBody @Valid SessaoParaCriarRequestV1 sessaoRequestV1) {
            SessaoCriadaResponseV1 sessaoResponseV1 = sessaoService.criarUmaNovaSessao(sessaoRequestV1);
        return new ResponseEntity<>(sessaoResponseV1, HttpStatus.CREATED);
}

    @PostMapping("/votar")
    @Operation(summary = "Votar em uma sessão")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description  = "Ok"),
            @ApiResponse(responseCode = "400", description = "Dados do payload inválidos."),
            @ApiResponse(responseCode = "422", description = "Dado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor. Por favor, tente novamente mais tarde.")
    })
    public ResponseEntity<SessaoVotadaResponseV1> votarEmUmaSessao(
            @NotEmpty(message = "Dado de votação é obrigatório e deve ser preenchido")
            @RequestBody @Valid SessaoParaVotarRequestV1 sessaoRequestV1) {
        SessaoVotadaResponseV1 sessaoVotadaResponseV1 = sessaoService.votarEmUmaSessao(sessaoRequestV1);
        return new ResponseEntity<>(sessaoVotadaResponseV1, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Total de votos da sessão")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description  = "Ok"),
            @ApiResponse(responseCode = "400", description = "Dados do payload inválidos."),
            @ApiResponse(responseCode = "422", description = "Dado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor. Por favor, tente novamente mais tarde.")
    })
    public ResponseEntity<SessaoTotalVotosResponseV1> totalDeVotosDaSessao(
            @NotEmpty(message = "Total de votos da votação é obrigatório e deve ser preenchido")
            @RequestBody @Valid SessaoParaSaberTotalVotosRequestV1 sessaoRequestV1) {
        SessaoTotalVotosResponseV1 sessaoTotalVotosResponseV1 = sessaoService.totalDeVotosDaSessao(sessaoRequestV1);
        return new ResponseEntity<>(sessaoTotalVotosResponseV1, HttpStatus.OK);
    }
}
