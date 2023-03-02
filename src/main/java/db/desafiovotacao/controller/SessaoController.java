package db.desafiovotacao.controller;

import db.desafiovotacao.model.Sessao;
import db.desafiovotacao.service.SessaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessao")
public class SessaoController {

    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService){
        this.sessaoService = sessaoService;
    }

    @PostMapping
    private ResponseEntity<Sessao> novaSessao(@RequestBody @Valid Sessao sessaoRequest){

        Sessao sessao = sessaoService.criarSessao(sessaoRequest);

        if(sessao == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(sessao, HttpStatus.CREATED);
    }

}
