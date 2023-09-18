package com.desafio.projeto_votacao.utils;

import com.desafio.projeto_votacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociadoValidator {
    
    private final AssociadoRepository associadoRepository;

/*
    public boolean nomeVazioOuNulo(String nome) {
        return Strings.isEmpty(nome);
    }
*/

/*    public boolean cpfVazioOuNulo(String cpf) {
        return Strings.isEmpty(cpf);
    }*/

    public String removerMascaraCPF(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    public boolean existeAssociadoComCPF(String cpf) {
        return associadoRepository.existsByCpf(cpf);
    }

}
