package com.db.desafio.controller;

import com.db.desafio.dto.SessaoVotacaoDto;
import com.db.desafio.dto.VotoDto;
import com.db.desafio.mapper.SessaoMapper;
import com.db.desafio.service.SessaoVotacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/sessoes")
public class SessaoVotacaoController {
    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;
    private static final SessaoMapper SESSAO_MAPPER = SessaoMapper.INSTANCE;

    @PostMapping
    ResponseEntity<Void> abrirSessao(@RequestParam Long pautaId) {
        sessaoVotacaoService.abrirSessao(pautaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    ResponseEntity<List<SessaoVotacaoDto>> obterSessoes() {
        return ResponseEntity.ok(SESSAO_MAPPER.listaSessaoParaListaSessaoDto(sessaoVotacaoService.obterSessoes()));
    }

    @GetMapping("/{id}")
    ResponseEntity<SessaoVotacaoDto> obterSessaoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(SESSAO_MAPPER.sessaoParaSessaoDto(sessaoVotacaoService.obterSessao(id)));
    }

    @PostMapping("/{id}/voto")
    ResponseEntity<Void> salvarVoto(@PathVariable Long id, @Valid @RequestBody VotoDto votoDto) {
        sessaoVotacaoService.votarSessao(id,votoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
