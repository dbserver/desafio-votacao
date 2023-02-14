package br.com.dbserver.votacao.v1.controller;

import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.service.AssociadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@AllArgsConstructor
@RequestMapping("v1/associado")
@RestController
public class AssociadoController {

    private final AssociadoService associadoService;

   @Operation(operationId = "Cadastrar", summary = "Cadastra um novo Associado", tags = { "Cadastrar" },
            parameters = { @Parameter()},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação bem Sucedida",
                            content = @Content(schema = @Schema(implementation = AssociadoResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Dados enviados podem ser inválidos")
                    })
    @PostMapping()
    public ResponseEntity<AssociadoResponse> salvarAssociado(@RequestBody @Valid AssociadoRequest associadoRequest){
       log.info("Metodo: salvarAssociado - ID Cliente: " + associadoRequest.getCpf());
        return new ResponseEntity<>(associadoService.salvar(associadoRequest), HttpStatus.OK);
    }
}
