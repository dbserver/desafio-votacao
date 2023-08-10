package com.db.votacao.api.service;

import com.db.votacao.api.interfaces.IAssociadoService;
import com.db.votacao.api.model.Associado;
import com.db.votacao.api.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService implements IAssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    public Associado criarAssociado(Associado associado) {
        return associadoRepository.save(associado);
    }
}
