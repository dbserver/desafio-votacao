package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.AssembleiaDTO;
import br.tec.db.votacao.service.AssembleiaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assembleias")
public class AssembleiaController {

    @Autowired
    private AssembleiaServiceImpl assembleiaServiceImpl;

    @PostMapping
    public ResponseEntity<AssembleiaDTO> criar(@RequestBody AssembleiaDTO assembleiaDTO) {
        return new ResponseEntity<>(assembleiaServiceImpl.criar(assembleiaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssembleiaDTO>> listar() {
        return new ResponseEntity<>(assembleiaServiceImpl.listar(), HttpStatus.OK);
    }
}
