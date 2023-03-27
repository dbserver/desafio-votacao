
package com.dbserver.desafiovotacao.controller;

import com.dbserver.desafiovotacao.dto.ClienteRequest;
import com.dbserver.desafiovotacao.dto.PautaRequest;
import com.dbserver.desafiovotacao.dto.PautaResponse;
import com.dbserver.desafiovotacao.model.Pauta;
import com.dbserver.desafiovotacao.model.Votante;
import com.dbserver.desafiovotacao.service.PautaService;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController {
    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Pauta> criarPauta(@Valid @RequestBody PautaRequest pautaRequest, BindingResult bindingResult) {

        return new ResponseEntity<>(pautaService.salvarPauta(pautaRequest), HttpStatus.CREATED);
    }

    @GetMapping("/numeroassociados/{id}")
    public ResponseEntity<Integer> totalVotantes(@PathVariable UUID id) {
        return new ResponseEntity<>(this.pautaService.totalVotantes(id), HttpStatus.OK);
    }
    @GetMapping("/mostrartodas")
    public ResponseEntity<Iterable<Pauta>> mostrarPauta() {
        return new ResponseEntity<>(this.pautaService.mostraPautas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaResponse> encontrarPautaPorId(@PathVariable UUID id) {
        Optional<Pauta> pauta = pautaService.encontrarPautaPorID(id);
        PautaResponse resposta;
        if (pauta.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            resposta = new PautaResponse(pauta.get());
        }
        return new ResponseEntity<>(resposta, HttpStatus.OK);
        
    }

    @GetMapping("/associados/{id}")
    public ResponseEntity<List<Votante>> encontrarVotantesNaPauta(@PathVariable UUID id) {
        Optional<Pauta> pauta = pautaService.encontrarPautaPorID(id);
        if (pauta.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pauta.get().getAssociados(), HttpStatus.OK);

    }

    @PostMapping("/adicionavotante/{hash}")
    public ResponseEntity<Pauta> adicionaVotanteNaPauta(@PathVariable String hash, @RequestBody ClienteRequest clienteRequest) {
        return new ResponseEntity<>(pautaService.adicionarAssociado(hash, clienteRequest), HttpStatus.OK);
    }

    @GetMapping("/finaliza/{hash}")
    public ResponseEntity<Pauta> finalizaPauta(@PathVariable String hash) {
        Optional<Pauta> pauta = pautaService.encontrarPautaPorHash(hash);
        if (pauta.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pautaService.finalizarPauta(hash), HttpStatus.OK);

    }


}

