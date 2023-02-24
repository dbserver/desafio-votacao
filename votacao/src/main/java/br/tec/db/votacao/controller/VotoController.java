package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.VotoDTO;
import br.tec.db.votacao.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votos")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping
    public ResponseEntity<VotoDTO> votar(@RequestBody VotoDTO votoDTO) {
        return votoDTO == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(votoService.votar(votoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VotoDTO> buscarVotoPorId(@PathVariable Long id) {
        return id == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(votoService.buscarVotoPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VotoDTO>> buscarTodosOsVotos() {
        return votoService.buscarTodosOsVotos().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(votoService.buscarTodosOsVotos(), HttpStatus.OK);
    }

    @GetMapping("/sessao/{id}")
    public ResponseEntity<List<VotoDTO>> buscarVotosPorSessaoDeVotacao(@PathVariable Long id) {
        return votoService.buscarVotosPorSessaoDeVotacao(id).isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(votoService.buscarVotosPorSessaoDeVotacao(id), HttpStatus.OK);
    }
}