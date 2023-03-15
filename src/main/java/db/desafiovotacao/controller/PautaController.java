package db.desafiovotacao.controller;

import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.dto.PautaResponse;
import db.desafiovotacao.model.Pauta;

import db.desafiovotacao.service.PautaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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

    
    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

    @PostMapping
    @Transactional
    private ResponseEntity<PautaResponse> cadastrarPauta(@RequestBody @Valid PautaRequest pautaRequest){

        System.out.println(pautaRequest.sessaoRequest().inicioSessao());
        System.out.println(pautaRequest.sessaoRequest().finalSessao());

        Pauta pauta = pautaService.cadastrarPauta(new Pauta(pautaRequest));

        if(pauta == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<Page<PautaResponse>> listarPautas(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){

        Page<Pauta> pautas = pautaService.listarPautas(pageable);

        if (pautas == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        Page<PautaResponse> pautasResponse = pautas.map(PautaResponse::new);

        return new ResponseEntity<>(pautasResponse, HttpStatus.OK);
    }


//    @GetMapping("{/id}")
//    private ResponseEntity<PautaResponse> buscarPauta(){
//
//        Pauta pauta = pautaService.buscarPautaPorID(id);
//
//        if (pauta == null)
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//
//        return new ResponseEntity<>(pauta.pautaResponse(), HttpStatus.OK);
//    }

}
