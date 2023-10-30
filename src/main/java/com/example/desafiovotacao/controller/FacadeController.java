package com.example.desafiovotacao.controller;

import com.example.desafiovotacao.dto.FacadeDTO;
import com.example.desafiovotacao.facade.CpfFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facade")
public class FacadeController {

    @Autowired
    private CpfFacade cpfFacade;

    @PostMapping("/{cpf}/validate")
    public ResponseEntity<FacadeDTO> validateCpf(@PathVariable String cpf){
        return cpfFacade.isCpfValid(cpf);
    }
}
