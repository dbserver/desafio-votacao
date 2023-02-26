package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.SessaoDeVotacaoDTO;
import br.tec.db.votacao.service.SessaoDeVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessao-de-votacao")
public class SessaoDeVotacaoController {

    @Autowired
    private SessaoDeVotacaoService sessaoDeVotacaoService;

    @PostMapping
    public ResponseEntity<SessaoDeVotacaoDTO> criarSessaoDeVotacao(@RequestBody SessaoDeVotacaoDTO sessaoDeVotacaoDto) {
        return sessaoDeVotacaoDto == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(sessaoDeVotacaoService.criarSessaoDeVotacao(sessaoDeVotacaoDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SessaoDeVotacaoDTO>> buscarTodasSessoesDeVotacao() {
        return sessaoDeVotacaoService.buscarTodasAsSessoesDeVotacao().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(sessaoDeVotacaoService.buscarTodasAsSessoesDeVotacao(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoDeVotacaoDTO> buscarSessaoDeVotacaoPorId(@PathVariable Long id) {
        return sessaoDeVotacaoService.buscarSessaoDeVotacaoPorId(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(sessaoDeVotacaoService.buscarSessaoDeVotacaoPorId(id), HttpStatus.OK);
    }

    @GetMapping("/pauta/{id}")
    public ResponseEntity<SessaoDeVotacaoDTO> buscarSessaoDeVotacaoPorPauta(@PathVariable Long id) {
        return sessaoDeVotacaoService.buscarSessaoDeVotacaoPorPauta(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(sessaoDeVotacaoService.buscarSessaoDeVotacaoPorPauta(id), HttpStatus.OK);
    }

    @PutMapping("/encerrar/{id}")
    public ResponseEntity<SessaoDeVotacaoDTO> encerrarSessaoDeVotacao(@PathVariable Long id) {
        sessaoDeVotacaoService.encerrarSessaoDeVotacao(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/resultado/{id}")
    public ResponseEntity<SessaoDeVotacaoDTO> calcularResultadoDaSessaoDeVotacao(@PathVariable Long id) {
        sessaoDeVotacaoService.calcularResultadoDaSessaoDeVotacao(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}