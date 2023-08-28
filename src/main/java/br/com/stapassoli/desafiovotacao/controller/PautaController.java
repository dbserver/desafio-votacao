package br.com.stapassoli.desafiovotacao.controller;

import br.com.stapassoli.desafiovotacao.dto.PautaDTO;
import br.com.stapassoli.desafiovotacao.entity.Pauta;
import br.com.stapassoli.desafiovotacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
@RequiredArgsConstructor
public class PautaController {

    private final PautaService pautaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pauta> cadastrarPauta(@RequestBody PautaDTO pautaDTO) {
        return pautaService.cadastrarPauta(pautaDTO);
    }

}
