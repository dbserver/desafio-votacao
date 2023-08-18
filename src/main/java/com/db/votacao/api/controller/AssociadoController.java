package com.db.votacao.api.controller;

import com.db.votacao.api.model.Associado;
import com.db.votacao.api.service.AssociadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    private final AssociadoService associadoService;

    @Autowired
    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @ApiOperation("Criar novo associado")
    @PostMapping
    public ResponseEntity<Associado> criarAssociado(@RequestBody Associado associadoRequest) {
        Associado associado = associadoService.criarAssociado(associadoRequest);
        return new ResponseEntity<>(associado, HttpStatus.CREATED);
    }


    @ApiOperation("Verifica se um associado com o CPF fornecido existe no banco de dados")
    @GetMapping("valida-cpf/{cpf}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Able to vote"),
            @ApiResponse(code = 404, message = "Unable to vote")
    })
    public ResponseEntity<String> verificarCpf(@PathVariable @CPF String cpf) {
        boolean associadoExists = associadoService.isCpfAssociadoExiste(cpf);

        if (associadoExists) {
            return new ResponseEntity<>("ABLE_TO_VOTE", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("UNABLE_TO_VOTE", HttpStatus.NOT_FOUND);
        }
    }
}
