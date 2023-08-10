package com.db.votacao.api.controller;

import com.db.votacao.api.model.Voto;
import com.db.votacao.api.service.VotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voto")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    @PostMapping
    public ResponseEntity<Voto> criarVoto(Voto votoRequest) {

        Voto voto = votoService.criarVoto(votoRequest);

        if (voto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(voto, HttpStatus.CREATED);
    }
}
