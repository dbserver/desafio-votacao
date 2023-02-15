package br.tec.db.desafio.api.v1.controller;

import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoRequestV1;
import br.tec.db.desafio.api.v1.dto.sessao.SessaoResponseV1;
import br.tec.db.desafio.business.service.PautaService;
import br.tec.db.desafio.business.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("api/v1/sessao")
@Tag(name = "Sessao")
public class SessaoControllerV1 {
    private final SessaoService sessaoService;

    public SessaoControllerV1(SessaoService sessaoService) {
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
    public ResponseEntity<SessaoResponseV1> cadastraSessao(
            @NotEmpty(message = "A sessão é obrigatória e deve ser preenchida")
            @RequestBody @Valid SessaoRequestV1 sessaoRequestV1) {
            SessaoResponseV1 sessaoResponseV1 = sessaoService.criarUmaNovaSessao(sessaoRequestV1);
        return new ResponseEntity<>(sessaoResponseV1, HttpStatus.CREATED);
}
}
