package br.com.adonias.desafiovotacao.controllers;

import br.com.adonias.desafiovotacao.business.AssociateValidationBusiness;
import br.com.adonias.desafiovotacao.dto.enums.AssociateValidationStatusDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("validation")
public class AssociateValidationController {

    @Autowired
    private AssociateValidationBusiness business;

    @ApiOperation(value = "Validates if a Associate is able/unable to vote")
    @GetMapping("/{cpf}")
    public ResponseEntity<AssociateValidationStatusDTO> validateCPF(@PathVariable String cpf){
        return business.validate(cpf);
    }
}
