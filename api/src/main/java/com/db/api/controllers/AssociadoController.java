package com.db.api.controllers;

import com.db.api.dtos.AssociadoDto;
import com.db.api.dtos.PautaDto;
import com.db.api.models.Associado;
import com.db.api.models.Pauta;
import com.db.api.services.AssociadoService;
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
@RequestMapping("/api/v1/associados")
public class AssociadoController {

    private AssociadoService associadoService;

    @PostMapping("/registrar")
    ResponseEntity<AssociadoDto> registrarAssociado(@RequestBody @Valid AssociadoDto associadoDto, UriComponentsBuilder uriBuilder) {
        associadoService.registrarAssociado(associadoDto);
        Associado associado = new Associado(associadoDto.getNome(),associadoDto.getNome());

        var uri = uriBuilder.path("/associados/{id}").buildAndExpand(associado.getId()).toUri();

        return ResponseEntity.created(uri).body(associadoDto);
    }

}
