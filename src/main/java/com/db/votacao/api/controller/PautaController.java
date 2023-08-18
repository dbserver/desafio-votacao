package com.db.votacao.api.controller;

import com.db.votacao.api.model.Pauta;
import com.db.votacao.api.service.PautaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @ApiOperation("Criar nova pauta")
    @PostMapping
    public ResponseEntity<Pauta> criarPauta(@RequestBody Pauta pautaRequest) {
        Pauta pauta = pautaService.criarPauta(pautaRequest);

        if (pauta != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(pauta);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation("Consultar pauta por nome")
    @GetMapping("/consultarNome/{nomePauta}")
    public ResponseEntity<Pauta> consultarPautaPorNome(
            @ApiParam("Nome da pauta a ser consultada") @PathVariable String nomePauta) {
        Pauta pauta = pautaService.consultarPautaPorNome(nomePauta);

        if (pauta != null) {
            return ResponseEntity.ok(pauta);
        } else {
            String mensagem = "Não foram encontradas pautas com o título: " + nomePauta;
            return ResponseEntity.notFound().header("mensagem", mensagem).build();
        }
    }

    @ApiOperation("Consultar pauta por ID")
    @GetMapping("/consultarId/{idPauta}")
    public ResponseEntity<Pauta> consultarPautaPorId(
            @ApiParam("ID da pauta a ser consultada") @PathVariable UUID idPauta) {
        Pauta pauta = pautaService.consultarPautaPorId(idPauta);

        if (pauta != null) {
            return ResponseEntity.ok(pauta);
        } else {
            String mensagem = "Não foi encontrada pauta com o ID: " + idPauta;
            return ResponseEntity.notFound().header("mensagem", mensagem).build();
        }
    }
}
