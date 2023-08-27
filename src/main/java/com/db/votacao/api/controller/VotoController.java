package com.db.votacao.api.controller;

import com.db.votacao.api.enums.EnumOpcoesVoto;
import com.db.votacao.api.model.Voto;
import com.db.votacao.api.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/voto")
public class VotoController {

    private final VotoService votoService;

    @Autowired
    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<Voto> criarVoto(@RequestBody Voto votoRequest) {
        Voto voto = votoService.criarVoto(votoRequest);

        if (voto == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(voto);
    }

    @GetMapping("/totalizador/{pautaId}")
    public ResponseEntity<Map<EnumOpcoesVoto, Long>> consultarTotalizadorVotos(
            @PathVariable UUID pautaId) {
        Map<EnumOpcoesVoto, Long> totalizador = votoService.calcularTotalizadorVotos(pautaId);

        return ResponseEntity.ok(totalizador);
    }
}
