package com.db.votacao.api.service;

import com.db.votacao.api.interfaces.IPautaService;
import com.db.votacao.api.model.Pauta;
import com.db.votacao.api.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;

    @Autowired
    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta criarPauta(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @Override
    public Pauta consultarPautaPorNome(String descricaoTituloPauta) {
        Optional<Pauta> pautaOptional = pautaRepository.findByDescricaoTituloPauta(descricaoTituloPauta);
        return pautaOptional.orElse(null);
    }

    public Pauta consultarPautaPorId(UUID idPauta) {
        Optional<Pauta> optionalPauta = pautaRepository.findById(idPauta);
        return optionalPauta.orElse(null);
    }
}
