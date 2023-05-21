package br.stapassoli.validadorcpf.controller;

import br.stapassoli.validadorcpf.dto.NumeroCpfDTO;
import br.stapassoli.validadorcpf.dto.NumeroCpfDTOResposta;
import br.stapassoli.validadorcpf.service.ValidadorCPFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validadoCPF")
public class ValidadorCPFController {

    @Autowired
    ValidadorCPFService validadorCPFService;

    @GetMapping
    public ResponseEntity<NumeroCpfDTOResposta> hellowWorld(@RequestBody NumeroCpfDTO cpf){
        return this.validadorCPFService.validarCPF(cpf.getNumeroCPF());
    }

}
