package com.fernandesclaudi.desafiovotacao.controller;

import com.fernandesclaudi.desafiovotacao.config.VersionApi;
import com.fernandesclaudi.desafiovotacao.dto.PautaDto;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.model.Pauta;
import com.fernandesclaudi.desafiovotacao.service.PautaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(VersionApi.VERSION + "/pauta")
public class PautaController {
    @Autowired
    private PautaService pautaService;
    @GetMapping("/{id}")
    public Pauta getPauta(@PathVariable Long id) {
        return pautaService.findById(id);
    }
    @GetMapping("/redator/{id}")
    public ResponseEntity<List<Pauta>> getPautasPorRedator(@PathVariable Long id) {
        return ResponseEntity.ok(pautaService.findAllByRedator(id));
    }

    @PostMapping
    public ResponseEntity<Pauta> save(@RequestBody @Valid PautaDto pautaDto) throws IRegistroNaoEncontradoException, IValorNaoInformadoException {
        return ResponseEntity.ok(pautaService.save(pautaDto));
    }
}
