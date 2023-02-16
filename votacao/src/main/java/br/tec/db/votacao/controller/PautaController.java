package br.tec.db.votacao.controller;

import br.tec.db.votacao.dto.PautaDTO;
import br.tec.db.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pautas")
public class PautaController {

    @Autowired
    private PautaService pautaService;

    @PostMapping
    public ResponseEntity<PautaDTO> criar(@RequestBody PautaDTO pautaDTO) {
        return new ResponseEntity<>(pautaService.criar(pautaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PautaDTO>> listar() {
        return new ResponseEntity<>(pautaService.listar(), HttpStatus.OK);
    }

    @GetMapping("/assembleias/{id}")
    public ResponseEntity<List<PautaDTO>> listarPorAssembleia(@PathVariable Long id) {
        return new ResponseEntity<>(pautaService.listarPorAssembleia(id), HttpStatus.OK);
    }
}