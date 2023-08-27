package com.db.votacao.api.controller;

import com.db.votacao.api.model.Sessao;
import com.db.votacao.api.service.SessaoService;
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

    @PostMapping("/incluirSessao")
    public ResponseEntity<Sessao> incluirSessao(@RequestBody Sessao sessaoRequest) {
        Sessao sessao = sessaoService.criarSessao(sessaoRequest);

        if (sessao != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(sessao);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idSessao}")
    public ResponseEntity<Sessao> consultarSessaoPorId(
            @PathVariable UUID idSessao) {
        Sessao sessao = sessaoService.consultarSessaoPorId(idSessao);

        if (sessao != null) {
            return ResponseEntity.ok(sessao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sessao>> consultarSessoesPorFiltros(
            @RequestParam(required = false) LocalDateTime dataCriacao,
            @RequestParam(required = false) LocalDateTime inicioSessao,
            @RequestParam(required = false) LocalDateTime finalSessao) {

        List<Sessao> sessoes = sessaoService.consultarSessoesPorFiltros(dataCriacao, inicioSessao, finalSessao);
        return ResponseEntity.ok(sessoes);
    }
}
