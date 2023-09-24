package com.db.api.controllers;

import com.db.api.dtos.PautaDto;
import com.db.api.models.Pauta;
import com.db.api.services.PautaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/pautas")
public class PautaController {

    private PautaService pautaService;

    @PostMapping("/cadastrar")
    ResponseEntity<PautaDto> criarPauta(@RequestBody @Valid PautaDto pautaDto, UriComponentsBuilder uriBuilder) {
        pautaService.criarNovaPauta(pautaDto);
        Pauta pauta = new Pauta(pautaDto.getTitulo(), pautaDto.getDescricao());

        var uri = uriBuilder.path("/pautas/{id}").buildAndExpand(pauta.getId()).toUri();

        return ResponseEntity.created(uri).body(pautaDto);
    }

}
