package db.desafiovotacao.controller;

import db.desafiovotacao.dto.ResultadoRequest;
import db.desafiovotacao.dto.ResultadoResponse;
import db.desafiovotacao.dto.VotoPautaRequest;
import db.desafiovotacao.dto.VotoPautaResponse;
import db.desafiovotacao.mappers.VotoPautaMapper;
import db.desafiovotacao.model.*;
import db.desafiovotacao.service.PautaService;
import db.desafiovotacao.service.VotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
@Validated
public class VotoController {

    private final VotoService votoService;
    private final PautaService pautaService;
    

    public VotoController(VotoService votoService, PautaService pautaService){
        this.votoService = votoService;
        this.pautaService = pautaService;        
    }

    @PostMapping
    public ResponseEntity<VotoPautaResponse> cadastrarVoto(@RequestBody @Valid VotoPautaRequest votoPautaRequest){

        Pauta pauta = pautaService.buscarPautaPorID(votoPautaRequest.idPauta());

        votoService.cadastrarVoto(VotoPautaMapper.mappearVotoPauta(votoPautaRequest, pauta), votoPautaRequest.cpf());

        return new ResponseEntity<>(new VotoPautaResponse(pauta.getId(), votoPautaRequest.cpf()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResultadoResponse> resultadoVotacao(@RequestBody @Valid ResultadoRequest resultadoRequest){

        Pauta pauta = pautaService.buscarPautaPorID(resultadoRequest.idPauta());

        Resultado resultado = votoService.resultadoVotacao(pauta);

        return new ResponseEntity<>(new ResultadoResponse(resultado), HttpStatus.OK);
    }
}
