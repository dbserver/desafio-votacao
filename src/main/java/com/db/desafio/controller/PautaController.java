package com.db.desafio.controller;

import com.db.desafio.dto.PautaDto;
import com.db.desafio.mapper.PautaMapper;
import com.db.desafio.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/pautas")
public class PautaController {
    @Autowired
    private PautaService pautaService;
    private static final PautaMapper PAUTA_MAPPER = PautaMapper.INSTANCE;

    @PostMapping
    ResponseEntity<Void> criarPauta(@Valid @RequestBody PautaDto pautaDto) {
        pautaService.criarPauta(PAUTA_MAPPER.pautDtoParaPauta(pautaDto));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarPauta(@PathVariable Long id) {
        pautaService.deletarPauta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    ResponseEntity<List<PautaDto>> obterPautas() {
        return ResponseEntity.ok(PAUTA_MAPPER.listaPautaParaListaPautaDto(pautaService.obterPautas()));
    }

    @GetMapping("/{id}")
    ResponseEntity<PautaDto> obterPautaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(PAUTA_MAPPER.pautaParaPautaDto(pautaService.obterPautaPorId(id)));
    }

}
