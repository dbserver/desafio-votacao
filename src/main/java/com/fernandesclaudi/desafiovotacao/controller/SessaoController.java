package com.fernandesclaudi.desafiovotacao.controller;

import com.fernandesclaudi.desafiovotacao.config.VersionApi;
import com.fernandesclaudi.desafiovotacao.dto.SessaoDto;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.model.Sessao;
import com.fernandesclaudi.desafiovotacao.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VersionApi.VERSION + "/sessao")
public class SessaoController {
    @Autowired
    private SessaoService sessaoService;

    @PostMapping("abrir")
    private ResponseEntity<Sessao> abrir(@RequestBody SessaoDto sessaoDto) throws IValorNaoInformadoException, IRegistroNaoEncontradoException {
        return ResponseEntity.ok(sessaoService.save(sessaoDto));
    }

    @GetMapping("abertas")
    private ResponseEntity<List<Sessao>> abertas() {
        return ResponseEntity.ok(sessaoService.sessoesAbertas());
    }

}
