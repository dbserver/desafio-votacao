package com.fernandesclaudi.desafiovotacao.controller;


import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.service.AssociadoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/associado")
public class AssociadoController {
    @Autowired

    public AssociadoService associadoService;

    @GetMapping("/{id}")
    public Associado getAssociado(@PathVariable @NotNull Long id) {
        return associadoService.findByCpf(id.toString());
    }
}
