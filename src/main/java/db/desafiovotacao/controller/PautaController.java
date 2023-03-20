package db.desafiovotacao.controller;

import db.desafiovotacao.dto.PautaAtualizarRequest;
import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.dto.PautaResponse;
import db.desafiovotacao.mappers.PautaMapper;
import db.desafiovotacao.model.Pauta;

import db.desafiovotacao.service.PautaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
@Validated
public class PautaController {

    private final PautaService pautaService;
    
    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<PautaResponse> cadastrarPauta(@RequestBody @Valid PautaRequest pautaRequest){

        Pauta pauta = pautaService.cadastrarPauta(PautaMapper.mapearPauta(pautaRequest));

        if(pauta == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Page<PautaResponse>> listarPautas(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){

        Page<Pauta> pautas = pautaService.listarPautas(pageable);

        if (pautas == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        Page<PautaResponse> pautasResponse = pautas.map(PautaResponse::new);

        return new ResponseEntity<>(pautasResponse, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<PautaResponse> atualizarPauta(@RequestBody @Valid PautaAtualizarRequest pautaAtualizarRequest){

        Pauta pautaAtualizada = pautaService.atualizarPauta(PautaMapper.mapearPauta(pautaAtualizarRequest));

        if (pautaAtualizada == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new PautaResponse(pautaAtualizada), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PautaResponse> buscarPauta(@PathVariable("id") Long id){

        Pauta pauta = pautaService.buscarPautaPorID(id);

        if (pauta == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PautaResponse> deletarPauta(@PathVariable("id") Long id){

        Pauta pauta = pautaService.deletarPauta(id);

        if (pauta == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.OK);
    }

}
