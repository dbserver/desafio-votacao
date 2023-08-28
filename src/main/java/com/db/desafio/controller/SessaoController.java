package com.db.desafio.controller;

import com.db.desafio.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "v1/sessoes")
public class SessaoController {
    @Autowired
    private SessaoService sessaoService;

    @PostMapping
    ResponseEntity<Void> abrirSessao(@RequestParam Long pautaId) {
        sessaoService.abrirSessao(pautaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
