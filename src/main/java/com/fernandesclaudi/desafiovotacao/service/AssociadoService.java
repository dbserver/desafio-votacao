package com.fernandesclaudi.desafiovotacao.service;

import com.fernandesclaudi.desafiovotacao.model.Associado;
import com.fernandesclaudi.desafiovotacao.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService {
    @Autowired
    private AssociadoRepository associadoRepository;

    public Associado findByCpf(String cpf) {
        return associadoRepository.findByCpf(cpf);
    }

    public Associado findById(Long id) {
        Optional<Associado> associado = associadoRepository.findById(id);
        return associado.orElse(null);
    }

    public Associado save(Associado associado) {
        return associadoRepository.save(associado);
    }
}
