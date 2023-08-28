package com.db.desafio.controller;


import com.db.desafio.dto.AssociadoDto;
import com.db.desafio.mapper.AssociadoMapper;
import com.db.desafio.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/associado")
public class AssociadoController {
    @Autowired
    private AssociadoService associadoService;
   @Autowired
    private static final AssociadoMapper ASSOCIADO_MAPPER = AssociadoMapper.INSTANCE;

    @PostMapping
    ResponseEntity<Void> criarAssociado(@Valid @RequestBody AssociadoDto associadoDto) {
        associadoService.criarAssociado(ASSOCIADO_MAPPER.associadoDtoParaAssociado(associadoDto));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarAssociado(@PathVariable Long id) {
        associadoService.deletarAssociado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    ResponseEntity<List<AssociadoDto>> obterAssociados() {
        return ResponseEntity.ok(ASSOCIADO_MAPPER.listaAssociadoParaListaAssociadoDto(associadoService.obterAssociados()));
    }

    @GetMapping("/{id}")
    ResponseEntity<AssociadoDto> obterAssociadoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ASSOCIADO_MAPPER.associadoParaAssociadoDto(associadoService.obterAssociadoPorId(id)));
    }
}
