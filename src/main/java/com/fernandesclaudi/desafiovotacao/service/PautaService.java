package com.fernandesclaudi.desafiovotacao.service;

import com.fernandesclaudi.desafiovotacao.model.Pauta;
import com.fernandesclaudi.desafiovotacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PautaService {
    @Autowired
    private PautaRepository PautaRepository;

    public Pauta findById(Long id) {
        Optional<Pauta> pauta = PautaRepository.findById(id);
        return pauta.orElse(null);
    }

    public Pauta save(Pauta pauta) {
        return PautaRepository.save(pauta);
    }
}
