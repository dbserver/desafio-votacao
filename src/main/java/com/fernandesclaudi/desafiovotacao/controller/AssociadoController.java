package com.fernandesclaudi.desafiovotacao.controller;


import com.fernandesclaudi.desafiovotacao.config.VersionApi;
import com.fernandesclaudi.desafiovotacao.dto.AssociadoDto;
import com.fernandesclaudi.desafiovotacao.dto.CpfDto;
import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.service.AssociadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Associado", description = "Associado API")
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

    @Operation(summary = "Validar Cpf", description = "Valida se um Cpf está habilitado para votar")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cpf válido para votar"),
            @ApiResponse(responseCode = "400", description = "Cpf inválido"),
            @ApiResponse(responseCode = "404", description = "Cpf não habilitado para votação")
    })
    @GetMapping("/is-valid/{cpf}")
    public ResponseEntity<String> isValid(
            @PathVariable
            @Valid
            @CPF(message = "Cpf inválido") String cpf) {
        return ResponseEntity.ok(associadoService.isValidCpf(cpf));
    }

    @Operation(summary = "Registrar associado", description = "Registra um novo associado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Associado registrado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Já existe um associado com o CPF informado")
    })
    @PostMapping
    public ResponseEntity<Associado> save(@RequestBody @Valid AssociadoDto associadoDto) {
        return ResponseEntity.ok(associadoService.save(associadoDto));
    }
}
