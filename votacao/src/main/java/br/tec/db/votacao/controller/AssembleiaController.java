package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.AssembleiaDTO;
import br.tec.db.votacao.service.AssembleiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assembleias")
public class AssembleiaController {

    @Autowired
    private AssembleiaService assembleiaService;

    @PostMapping
    public ResponseEntity<AssembleiaDTO> criarAssembleia(@RequestBody AssembleiaDTO assembleiaDTO) {
        return assembleiaDTO == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(assembleiaService.criarAssembleia(assembleiaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssembleiaDTO>> buscarTodasAssembleias() {
        return assembleiaService.buscarTodasAssembleias().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(assembleiaService.buscarTodasAssembleias(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssembleiaDTO> buscarAssembleiaPorId(@PathVariable Long id) {
        return assembleiaService.buscarAssembleiaPorId(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(assembleiaService.buscarAssembleiaPorId(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssembleiaDTO> finalizarAssembleia(@PathVariable Long id) {
        assembleiaService.finalizarAssembleia(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
