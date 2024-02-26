package com.fernandesclaudi.desafiovotacao.controller;


import com.fernandesclaudi.desafiovotacao.config.VersionApi;
import com.fernandesclaudi.desafiovotacao.dto.AssociadoDto;
import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.service.AssociadoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VersionApi.VERSION + "/associado")
public class AssociadoController {
    @Autowired

    public AssociadoService associadoService;

    @GetMapping("/{id}")
    public Associado getAssociado(
            @Min(value = 1, message = "Valor mínimo para o id deve ser 1")
            @PathVariable Long id) {
        return associadoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Associado> save(@RequestBody @Valid AssociadoDto associadoDto) {
        return ResponseEntity.ok(associadoService.save(associadoDto));
    }
}
