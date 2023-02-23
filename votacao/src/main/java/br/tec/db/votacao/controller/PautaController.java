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
    public ResponseEntity<PautaDTO> criarPauta(@RequestBody PautaDTO pautaDTO) {
        return pautaDTO == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(pautaService.criarPauta(pautaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaDTO> buscarPautaPorID(@PathVariable Long id) {
        return pautaService.buscarPautaPorId(id) == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(pautaService.buscarPautaPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PautaDTO>> buscarTodasAsPautas() {
        return pautaService.buscarTodasAsPautas().isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(pautaService.buscarTodasAsPautas(), HttpStatus.OK);
    }

    @GetMapping("/assembleia/{id}")
    public ResponseEntity<List<PautaDTO>> buscarPautasPorAssembleia(@PathVariable Long id) {
        return pautaService.buscarPautasPorAssembleia(id).isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(pautaService.buscarPautasPorAssembleia(id), HttpStatus.OK);
    }
}