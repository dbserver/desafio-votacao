package com.desafiovotacao.entrypoint;

import com.desafiovotacao.dto.SessaoPautaDTO;
import com.desafiovotacao.service.interfaces.ICriarSessaoPautaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/sessao-pauta")
public class SessaoPautaController {

    private final ICriarSessaoPautaService criarSessaoPautaService;

    public SessaoPautaController(
            ICriarSessaoPautaService criarSessaoPautaService
    ) {
        this.criarSessaoPautaService = criarSessaoPautaService;
    }

    @ApiOperation(value = "Criar uma sessão")
    @PostMapping(produces="application/json")
    public ResponseEntity<SessaoPautaDTO> salvar(@Validated @RequestBody SessaoPautaDTO sessaoPautaDTO) {
        SessaoPautaDTO sessaoPautaCriada = this.criarSessaoPautaService.criar(sessaoPautaDTO);
        return ResponseEntity.ok(sessaoPautaCriada);
    }
}
