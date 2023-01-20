package br.com.aplicationvotacao.aplicationvotacao.controller;

import br.com.aplicationvotacao.aplicationvotacao.dto.VotoDTO;
import br.com.aplicationvotacao.aplicationvotacao.model.Voto;
import br.com.aplicationvotacao.aplicationvotacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    VotoService votoService;

    @PostMapping
    public ResponseEntity<Voto> salvarVoto(@Valid @RequestBody VotoDTO votoDTO){
        return new ResponseEntity<>(votoService.salvarVoto(votoDTO), HttpStatus.CREATED);
    }
}
