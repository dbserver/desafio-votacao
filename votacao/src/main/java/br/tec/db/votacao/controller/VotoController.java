package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.VotoDTO;
import br.tec.db.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<VotoDTO> criarVoto(@RequestBody VotoDTO votoDTO) {
        return votoDTO == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(votoService.criarVoto(votoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/sessao/{id}/sim")
    public ResponseEntity<Long> calcularVotosSimPorSessaoDeVotacao(@PathVariable Long id) {
        return votoService.calcularVotosSimPorSessaoDeVotacao(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(votoService.calcularVotosSimPorSessaoDeVotacao(id), HttpStatus.OK);
    }

    @GetMapping("/sessao/{id}/nao")
    public ResponseEntity<Long> calcularVotosNaoPorSessaoDeVotacao(@PathVariable Long id) {
        return votoService.calcularVotosNaoPorSessaoDeVotacao(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(votoService.calcularVotosNaoPorSessaoDeVotacao(id), HttpStatus.OK);
    }
}