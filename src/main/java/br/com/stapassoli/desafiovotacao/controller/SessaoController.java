package br.com.stapassoli.desafiovotacao.controller;

import br.com.stapassoli.desafiovotacao.dto.SessaoDTO;
import br.com.stapassoli.desafiovotacao.entity.Sessao;
import br.com.stapassoli.desafiovotacao.enums.VotoStatus;
import br.com.stapassoli.desafiovotacao.exceptions.PautaException;
import br.com.stapassoli.desafiovotacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessao")
@RequiredArgsConstructor
public class SessaoController {

    private final SessaoService sessaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sessao> cadastrarSessao(@RequestBody SessaoDTO sessaoDTO) {
        return sessaoService.cadastrarSessao(sessaoDTO);
    }

    @GetMapping("/{pautaId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<VotoStatus> resultadoVotacao(@PathVariable Long pautaId) throws Exception {
        return sessaoService.resultadoVotacao(pautaId);
    }

}
