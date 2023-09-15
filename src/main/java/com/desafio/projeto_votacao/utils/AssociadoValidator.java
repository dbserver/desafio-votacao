package com.desafio.projeto_votacao.utils;

import com.desafio.projeto_votacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssociadoValidator {

    private final CpfValidator cpfValidator;
    private final AssociadoRepository associadoRepository;

    public boolean nomeVazioOuNulo(String nome) {
        return nome == null || nome.isEmpty();
    }

    public boolean cpfVazioOuNulo(String cpf) {
        return cpf == null || cpf.isEmpty();
    }

    public String removerMascaraCPF(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    public boolean existeAssociadoComCPF(String cpf) {
        return associadoRepository.existsByCpf(cpf);
    }

}
