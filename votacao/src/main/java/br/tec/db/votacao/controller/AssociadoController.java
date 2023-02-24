package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.AssociadoDTO;
import br.tec.db.votacao.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @PostMapping
    public ResponseEntity<AssociadoDTO> salvarAssociado(@RequestBody AssociadoDTO associadoDTO) {
        return associadoDTO == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(associadoService.salvarAssociado(associadoDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDTO> buscarAssociadoPorID(@PathVariable Long id) {
        return associadoService.buscarAssociadoPorId(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(associadoService.buscarAssociadoPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AssociadoDTO>> buscarTodosOsAssociados() {
        return associadoService.buscarTodosOsAssociados().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(associadoService.buscarTodosOsAssociados(), HttpStatus.OK);
    }

}