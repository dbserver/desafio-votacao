package db.desafiovotacao.controller;



import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.dto.PautaResponse;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.model.Pauta;

import db.desafiovotacao.service.PautaService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    private final PautaService pautaService;

    
    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

    @PostMapping
    private ResponseEntity<PautaResponse> cadastrarPauta(@RequestBody @Valid PautaRequest pautaRequest){

        System.out.println(pautaRequest.sessaoRequest().inicioSessao());
        System.out.println(pautaRequest.sessaoRequest().finalSessao());

        Pauta pauta = pautaService.cadastrarPauta(new Pauta(pautaRequest));

        if(pauta == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(pauta.pautaResponse(), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<PautaResponse>> listarPautas(){

        List<Pauta> pautas = pautaService.listarPautas();

        if (pautas == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        List<PautaResponse> pautasResponse = pautas.stream().map(Pauta::pautaResponse).toList();

        return new ResponseEntity<>(pautasResponse, HttpStatus.OK);
    }



}
