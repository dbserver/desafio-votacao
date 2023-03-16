package db.desafiovotacao.controller;

import db.desafiovotacao.dto.AssociadoRequest;
import db.desafiovotacao.dto.AssociadoResponse;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.service.AssociadoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService){
        this.associadoService = associadoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AssociadoResponse> cadastrarAssociado(@RequestBody @Valid AssociadoRequest associadoRequest){

        Associado associado = associadoService.criarAssociado(new Associado(associadoRequest));

        if(associado == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new AssociadoResponse(associado), HttpStatus.CREATED);
    }
}
