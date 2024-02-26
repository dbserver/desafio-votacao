package com.fernandesclaudi.desafiovotacao.controller;

import com.fernandesclaudi.desafiovotacao.config.VersionApi;
import com.fernandesclaudi.desafiovotacao.dto.PautaDto;
import com.fernandesclaudi.desafiovotacao.exceptions.IRegistroNaoEncontradoException;
import com.fernandesclaudi.desafiovotacao.exceptions.IValorNaoInformadoException;
import com.fernandesclaudi.desafiovotacao.model.Pauta;
import com.fernandesclaudi.desafiovotacao.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pauta", description = "Pauta API")
@RestController
@RequestMapping(VersionApi.VERSION + "/pauta")
public class PautaController {
    @Autowired
    private PautaService pautaService;
    @GetMapping("/{id}")
    public Pauta getPauta(@PathVariable Long id) {
        return pautaService.findById(id);
    }
    @Operation(
            summary = "Pautas por redator",
            description = "Retorna a lista de todas as pautas registradas por um associado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listagem de pautas"),
    })
    @GetMapping("/redator/{id}")
    public ResponseEntity<List<Pauta>> getPautasPorRedator(@PathVariable Long id) {
        return ResponseEntity.ok(pautaService.findAllByRedator(id));
    }

    @Operation(
            summary = "Registrar pauta",
            description = "Registra uma pauta para um associado"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pauta registrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Associado não encontrado"),
    })
    @PostMapping
    public ResponseEntity<Pauta> save(@RequestBody @Valid PautaDto pautaDto) throws IRegistroNaoEncontradoException, IValorNaoInformadoException {
        return ResponseEntity.ok(pautaService.save(pautaDto));
    }
}
