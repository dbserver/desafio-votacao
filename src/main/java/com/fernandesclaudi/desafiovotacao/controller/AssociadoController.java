package com.fernandesclaudi.desafiovotacao.controller;


import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.service.AssociadoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${VersionApi.VERSION}/associado")
public class AssociadoController {
    @Autowired

    public AssociadoService associadoService;

    @GetMapping("/{id}")
    public Associado getAssociado(@PathVariable @NotNull Long id) {
        return associadoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Associado> save(@RequestBody Associado associado) {
        return ResponseEntity.ok(associadoService.save(associado));
    }
}
