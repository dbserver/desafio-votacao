package br.com.aplicationvotacao.aplicationvotacao.controller;


import br.com.aplicationvotacao.aplicationvotacao.dto.SessaoVotacaoDTO;
import br.com.aplicationvotacao.aplicationvotacao.model.SessaoVotacao;
import br.com.aplicationvotacao.aplicationvotacao.service.SessaoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sessaoVotacao")
public class SessaoVotacaoController {

    @Autowired
    SessaoVotacaoService sessaoVotacaoService;

    @PostMapping
    public ResponseEntity<SessaoVotacao> salvarSessaoVotacao (@Valid @RequestBody SessaoVotacaoDTO sessaoVotacaoDTO){
        return new ResponseEntity<>(sessaoVotacaoService.salvarSessaoVotacao(sessaoVotacaoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SessaoVotacao>> buscarTodasSessoes(){
        List<SessaoVotacao> list = sessaoVotacaoService.buscarSessoes();
        return list.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<SessaoVotacao>> buscarSessoesAbertas(){
        List<SessaoVotacao> list = sessaoVotacaoService.buscarSessoesAbertas();
        //List<SessaoVotacao> list = sessaoVotacaoService.buscarSessoes();
        return list.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(list, HttpStatus.OK);
    }

}

