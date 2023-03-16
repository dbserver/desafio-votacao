package db.desafiovotacao.controller;

import db.desafiovotacao.dto.VotoPautaRequest;
import db.desafiovotacao.dto.VotoPautaResponse;
import db.desafiovotacao.model.*;
import db.desafiovotacao.service.AssociadoPautaService;
import db.desafiovotacao.service.PautaService;
import db.desafiovotacao.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voto")
@Validated
public class VotoController {

    private final VotoService votoService;
    private final PautaService pautaService;

    private final AssociadoPautaService associadoPautaService;

    public VotoController(VotoService votoService, PautaService pautaService, AssociadoPautaService associadoPautaService){
        this.votoService = votoService;
        this.pautaService = pautaService;
        this.associadoPautaService = associadoPautaService;
    }

    @PostMapping
    public ResponseEntity<VotoPautaResponse> cadastrarVoto(@RequestBody @Valid VotoPautaRequest votoPautaRequest){

        Pauta pauta = pautaService.buscarPautaPorID(votoPautaRequest.idPauta());

        VotoPauta votoPauta = votoService.cadastrarVoto(new VotoPauta(votoPautaRequest, pauta), votoPautaRequest.cpf());

        if(votoPauta == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new VotoPautaResponse(pauta.getId(), votoPautaRequest.cpf()), HttpStatus.CREATED);
    }
}
