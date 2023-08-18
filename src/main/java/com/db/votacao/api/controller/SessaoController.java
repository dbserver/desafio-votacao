package com.db.votacao.api.controller;

import com.db.votacao.api.model.Sessao;
import com.db.votacao.api.service.SessaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @ApiOperation("Incluir uma nova sessão")
    @PostMapping("/incluirSessao")
    public ResponseEntity<Sessao> incluirSessao(@RequestBody Sessao sessaoRequest) {
        Sessao sessao = sessaoService.criarSessao(sessaoRequest);

        if (sessao != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(sessao);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation("Consultar sessão por ID")
    @GetMapping("/{idSessao}")
    public ResponseEntity<Sessao> consultarSessaoPorId(
            @ApiParam("ID da sessão a ser consultada") @PathVariable UUID idSessao) {
        Sessao sessao = sessaoService.consultarSessaoPorId(idSessao);

        if (sessao != null) {
            return ResponseEntity.ok(sessao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation("Consultar sessões por filtros")
    @GetMapping
    public ResponseEntity<List<Sessao>> consultarSessoesPorFiltros(
            @ApiParam("Data de criação da sessão") @RequestParam(required = false) LocalDateTime dataCriacao,
            @ApiParam("Data de início da sessão") @RequestParam(required = false) LocalDateTime inicioSessao,
            @ApiParam("Data de encerramento da sessão") @RequestParam(required = false) LocalDateTime finalSessao) {

        List<Sessao> sessoes = sessaoService.consultarSessoesPorFiltros(dataCriacao, inicioSessao, finalSessao);
        return ResponseEntity.ok(sessoes);
    }
}
