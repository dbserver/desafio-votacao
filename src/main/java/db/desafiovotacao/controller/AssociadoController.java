package db.desafiovotacao.controller;

import db.desafiovotacao.dto.AssociadoRequest;
import db.desafiovotacao.dto.AssociadoResponse;
import db.desafiovotacao.mappers.AssociadoMapper;
import db.desafiovotacao.model.Associado;
import db.desafiovotacao.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService){
        this.associadoService = associadoService;
    }

    @PostMapping
    public ResponseEntity<AssociadoResponse> cadastrarAssociado(@RequestBody @Valid AssociadoRequest associadoRequest){

        Associado associado = associadoService.criarAssociado(AssociadoMapper.mappearAssociado(associadoRequest));

        if(associado == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new AssociadoResponse(associado), HttpStatus.CREATED);
    }
}
