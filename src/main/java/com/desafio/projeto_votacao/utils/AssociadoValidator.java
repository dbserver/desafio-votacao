package com.desafio.projeto_votacao.utils;

import com.desafio.projeto_votacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociadoValidator {
    
    private final AssociadoRepository associadoRepository;

    public String removerMascaraCPF(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    public boolean existeAssociadoComCPF(String cpf) {
        return associadoRepository.existsByCpf(cpf);
    }

}
