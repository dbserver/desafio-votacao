package com.fernandesclaudi.desafiovotacao.controller;

import com.fernandesclaudi.desafiovotacao.config.VersionApi;
import com.fernandesclaudi.desafiovotacao.dto.ContabilizacaoDto;
import com.fernandesclaudi.desafiovotacao.dto.SessaoDto;
import com.fernandesclaudi.desafiovotacao.dto.VotoDto;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.model.Sessao;
import com.fernandesclaudi.desafiovotacao.model.Voto;
import com.fernandesclaudi.desafiovotacao.service.SessaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
    private ResponseEntity<Sessao> abrir(@Valid @RequestBody SessaoDto sessaoDto) throws IValorNaoInformadoException, IRegistroNaoEncontradoException {
        return ResponseEntity.ok(sessaoService.abrir(sessaoDto));
    }

    @GetMapping("abertas")
    private ResponseEntity<List<Sessao>> abertas() {
        return ResponseEntity.ok(sessaoService.sessoesAbertas());
    }

    @PostMapping("votar")
    private ResponseEntity<Voto> votar(@RequestBody @Valid VotoDto votoDto) {
        return ResponseEntity.ok(sessaoService.registrarVoto(votoDto));
    }

    @GetMapping("contabilizar/{idSessao}")
    private ResponseEntity<ContabilizacaoDto> contabilizar(
            @PathVariable
            @Valid
            @Min(value = 1, message = "Id da sessão deve ser maior ou igual a um") Long idSessao) {
        return ResponseEntity.ok(sessaoService.contabilizarVotos(idSessao));
    }

}