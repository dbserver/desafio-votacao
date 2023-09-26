package com.db.api.controllers;

import com.db.api.dtos.SessaoDto;
import com.db.api.dtos.response.SessaoResponse;
import com.db.api.models.Sessao;
import com.db.api.services.SessaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/sessoes")
public class SessaoController {

    private final SessaoService sessaoService;

    @Transactional
    @PostMapping("/iniciarVotacao")
    public ResponseEntity<String> iniciarSessaoVotacao(@RequestBody @Valid SessaoDto sessaoDto, UriComponentsBuilder uriBuilder) {

        Sessao sessao = sessaoService.iniciarSessaoVotacao(sessaoDto.getPauta().getTitulo(), sessaoDto.getDataEncerramento());
        var uri = uriBuilder.path("/sessoes/{id}").buildAndExpand(sessao.getId()).toUri();

        return ResponseEntity.created(uri).body("Iniciada votação da pauta: " + sessaoDto.getPauta().getTitulo());
    }

}
