package br.tec.db.desafio.api.v1.controller;

import br.tec.db.desafio.api.v1.dto.associado.AssociadoRequestV1;
import br.tec.db.desafio.api.v1.dto.associado.AssociadoResponseV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaRequestV1;
import br.tec.db.desafio.api.v1.dto.pauta.PautaResponseV1;
import br.tec.db.desafio.business.service.AssociadoService;
import br.tec.db.desafio.business.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("api/v1/associado")
@Tag(name = "Associado")
public class AssociadoControllerV1 {
    private final AssociadoService associadoService;

    public AssociadoControllerV1(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping
    @Operation(summary = "Cadastro de associado")
    @ApiResponses({
            @ApiResponse(responseCode  = "200", description  = "Ok"),
            @ApiResponse(responseCode = "400", description = "Dados do payload inválidos."),
            @ApiResponse(responseCode = "422", description = "Dado não encontrado"),
            @ApiResponse(responseCode = "500", description = "Ocorreu um erro inesperado no servidor. Por favor, tente novamente mais tarde.")
    })
    public ResponseEntity<AssociadoResponseV1> cadastraAssociado(
            @NotEmpty(message = "O associado é obrigatório e deve ser preenchida")
            @RequestBody @Valid AssociadoRequestV1 associadoRequestV1) {
            AssociadoResponseV1 associadoResponseV1 = associadoService.criarUmNovoAssociado(associadoRequestV1);
        return new ResponseEntity<>(associadoResponseV1, HttpStatus.CREATED);
    }
}
