package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.CreatedAssociateDTO;
import com.example.desafiovotacao.dto.RegisterAssociateDTO;
import com.example.desafiovotacao.service.implementations.AssociateServiceImpl;
import com.example.desafiovotacao.service.interfaces.AssociateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Associados", description = "Crie associados")
@RestController
@RequestMapping("/associate")
@RequiredArgsConstructor
public class AssociateController {

    private final AssociateServiceImpl associateService;

    @Operation(
            summary = "Cadastrar um novo associado",
            description = "Realiza o cadastro de um novo associado caso não exista"
    )
    @PostMapping("/create")
    @ApiResponse(
            responseCode = "201",
            content = @Content(schema = @Schema(implementation = CreatedAssociateDTO.class))
    )
    private ResponseEntity<CreatedAssociateDTO> create(@RequestBody RegisterAssociateDTO associate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(associateService.create(associate));
    }

}
