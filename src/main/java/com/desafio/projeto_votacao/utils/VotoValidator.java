package com.desafio.projeto_votacao.utils;

import com.desafio.projeto_votacao.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotoValidator {

    private final VotoRepository votoRepository;

    public boolean associadoJaVotou(String cpf) {
        return votoRepository.existsByAssociadoCpf(cpf);
    }

}
