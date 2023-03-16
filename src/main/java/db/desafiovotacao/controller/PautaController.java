package db.desafiovotacao.controller;

import db.desafiovotacao.dto.PautaAtualizacaoRequest;
import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.dto.PautaResponse;
import db.desafiovotacao.model.Pauta;

import db.desafiovotacao.service.PautaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PautaResponse> cadastrarPauta(@RequestBody @Valid PautaRequest pautaRequest){

        Pauta pauta = pautaService.cadastrarPauta(new Pauta(pautaRequest));

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
    @Transactional
    public ResponseEntity<PautaResponse> atualizarPauta(@RequestBody @Valid PautaAtualizacaoRequest pautaAtualizacaoRequest){

        Pauta pauta = pautaService.buscarPautaPorID(pautaAtualizacaoRequest.id());

        if (pauta == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        pauta.atualizar(pautaAtualizacaoRequest);

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PautaResponse> buscarPauta(@PathVariable("id") Long id){

        Pauta pauta = pautaService.buscarPautaPorID(id);

        if (pauta == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.OK);
    }

}
