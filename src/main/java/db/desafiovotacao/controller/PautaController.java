package db.desafiovotacao.controller;

import db.desafiovotacao.dto.PautaRequest;
import db.desafiovotacao.dto.PautaResponse;
import db.desafiovotacao.model.Pauta;
import db.desafiovotacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

    @PostMapping
    private ResponseEntity<Pauta> novaPauta(@RequestBody Pauta pautaRequest){

        Pauta pauta = pautaService.criarPauta(pautaRequest);

        if(pauta == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(pauta, HttpStatus.CREATED);
    }
}
