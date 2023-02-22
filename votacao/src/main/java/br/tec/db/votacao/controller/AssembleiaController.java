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
        return new ResponseEntity<>(assembleiaService.criarAssembleia(assembleiaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssembleiaDTO>> buscarTodasAssembleias() {
        return new ResponseEntity<>(assembleiaService.buscarTodasAssembleias(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssembleiaDTO> buscarAssembleiaPorId(@PathVariable Long id) {
        return new ResponseEntity<>(assembleiaService.buscarAssembleiaPorId(id), HttpStatus.OK);
    }
}
