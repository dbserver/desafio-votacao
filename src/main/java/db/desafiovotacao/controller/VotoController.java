package db.desafiovotacao.controller;

import db.desafiovotacao.model.Voto;
import db.desafiovotacao.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voto")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService){
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<Voto> novoVoto(@RequestBody @Valid Voto votoRequest){

        Voto voto = votoService.criarVoto(votoRequest);

        if(voto == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(voto, HttpStatus.CREATED);
    }
}
