package com.db.votacao.api.service;

import com.db.votacao.api.interfaces.IVotoService;
import com.db.votacao.api.model.Voto;
import com.db.votacao.api.repository.VotoRepository;
import org.springframework.stereotype.Service;

@Service
public class VotoService implements IVotoService {

    private final VotoRepository votoRepository;

    public VotoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    public Voto criarVoto(Voto voto) {
        return votoRepository.save(voto);
    }

}
