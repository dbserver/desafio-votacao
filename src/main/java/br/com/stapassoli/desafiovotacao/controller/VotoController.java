package br.com.stapassoli.desafiovotacao.controller;

import br.com.stapassoli.desafiovotacao.dto.VotoDTO;
import br.com.stapassoli.desafiovotacao.service.SessaoService;
import br.com.stapassoli.desafiovotacao.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/voto")
@RequiredArgsConstructor
public class VotoController {

    private final SessaoService sessaoService;
    private final VotoService votoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> cadastrarSessao(@RequestBody VotoDTO votoDTO) {
        try {
            return votoService.cadastrarVoto(votoDTO);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

}
