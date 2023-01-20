package br.com.aplicationvotacao.aplicationvotacao.controller;

import br.com.aplicationvotacao.aplicationvotacao.dto.PautaDTO;
import br.com.aplicationvotacao.aplicationvotacao.dto.ResultadoPautaDTO;
import br.com.aplicationvotacao.aplicationvotacao.model.Pauta;
import br.com.aplicationvotacao.aplicationvotacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    PautaService pautaService;

    @PostMapping
    public ResponseEntity<Pauta> salvarPauta(@Valid @RequestBody PautaDTO pautaDTO) {
        return new ResponseEntity<>(pautaService.salvarPauta(pautaDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Pauta>> listarPautas(){
        List<Pauta> pautas = this.pautaService.buscarPautas();
        return pautas.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(pautas, HttpStatus.OK);
    }

    @GetMapping("/resultado/{id}")
    public ResponseEntity<ResultadoPautaDTO> resultadoVotacao(@PathVariable Long id){
        ResultadoPautaDTO pautaResultado = this.pautaService.resultadoPauta(id);
        return new ResponseEntity<>(pautaResultado, HttpStatus.OK);
    }

}

