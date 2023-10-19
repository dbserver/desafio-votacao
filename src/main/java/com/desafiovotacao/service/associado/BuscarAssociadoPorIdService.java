
package com.desafiovotacao.service.associado;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.repository.AssociadoRepository;
import com.desafiovotacao.service.interfaces.IBuscarAssociadoPorIdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscarAssociadoPorIdService implements IBuscarAssociadoPorIdService {

    private final AssociadoRepository associadoRepository;

    public BuscarAssociadoPorIdService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Associado buscar(String id) {
        return this.associadoRepository.findById(id).orElse(null);
    }
}
