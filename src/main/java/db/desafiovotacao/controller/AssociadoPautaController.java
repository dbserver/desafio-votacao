package db.desafiovotacao.controller;

import db.desafiovotacao.dto.AssociadoPautaRequest;
import db.desafiovotacao.dto.AssociadoPautaResponse;
import db.desafiovotacao.model.AssociadoPauta;
import db.desafiovotacao.service.AssociadoPautaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associado/pauta")
@Validated
public class AssociadoPautaController {

    private final AssociadoPautaService associadoPautaService;

    public AssociadoPautaController(AssociadoPautaService associadoPautaService){
        this.associadoPautaService = associadoPautaService;
    }

    @PostMapping
    public ResponseEntity<AssociadoPautaResponse> cadastrarAssociadoNaPauta(@RequestBody @Valid AssociadoPautaRequest associadoPautaRequest){

        AssociadoPauta associadoPauta = associadoPautaService.cadastarAssociadoNaPauta(associadoPautaRequest);

        if (associadoPauta == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new AssociadoPautaResponse(associadoPauta), HttpStatus.CREATED);
    }

}
