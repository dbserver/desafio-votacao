package com.db.desafio.controller;

import com.db.desafio.dto.SessaoDto;
import com.db.desafio.mapper.SessaoMapper;
import com.db.desafio.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "v1/sessoes")
public class SessaoController {
    @Autowired
    private SessaoService sessaoService;
    private static final SessaoMapper SESSAO_MAPPER = SessaoMapper.INSTANCE;

    @PostMapping
    ResponseEntity<Void> abrirSessao(@RequestParam Long pautaId) {
        sessaoService.abrirSessao(pautaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping
    ResponseEntity<List<SessaoDto>> obterSessoes() {
        return ResponseEntity.ok(SESSAO_MAPPER.listaSessaoParaListaSessaoDto(sessaoService.obterSessoes()));
    }

    @GetMapping("/{id}")
    ResponseEntity<SessaoDto> obterSessaoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(SESSAO_MAPPER.sessaoParaSessaoDto(sessaoService.obterSessao(id)));
    }

}
