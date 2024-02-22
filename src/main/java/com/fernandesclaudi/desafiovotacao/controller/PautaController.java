package com.fernandesclaudi.desafiovotacao.controller;

import com.fernandesclaudi.desafiovotacao.model.Pauta;
import com.fernandesclaudi.desafiovotacao.service.PautaService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${VersionApi.VERSION}/pauta")
public class PautaController {
    @Autowired
    private PautaService pautaService;
    @GetMapping("/{id}")
    public Pauta getPauta(@PathVariable @NotNull Long id) {
        return pautaService.findById(id);
    }

    @PostMapping
    public Pauta save(@RequestBody Pauta pauta) {
        return pautaService.save(pauta);
    }
}
