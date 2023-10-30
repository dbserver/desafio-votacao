package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.FacadeDTO;
import com.example.desafiovotacao.facade.CpfFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Facade CPF", description = "Utilizar facade para validar CPF")
@RestController
@RequestMapping("/facade")
public class FacadeController {

    @Autowired
    private CpfFacade cpfFacade;

    @Operation(
            summary = "Validar CPF",
            description = "Realiza a validação de um CPF pela facade/fake client"
    )
    @PostMapping("/{cpf}/validate")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = FacadeDTO.class))
    )
    public ResponseEntity<FacadeDTO> validateCpf(@PathVariable String cpf){
        return cpfFacade.isCpfValid(cpf);
    }
}
