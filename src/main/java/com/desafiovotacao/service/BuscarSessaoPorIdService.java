
package com.desafiovotacao.service;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.domain.SessaoPauta;
import com.desafiovotacao.repository.AssociadoRepository;
import com.desafiovotacao.repository.SessaoPautaRepository;
import com.desafiovotacao.service.interfaces.IBuscarAssociadoPorIdService;
import com.desafiovotacao.service.interfaces.IBuscarSessaoPorIdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscarSessaoPorIdService implements IBuscarSessaoPorIdService {

    private final SessaoPautaRepository sessaoPautaRepository;

    public BuscarSessaoPorIdService(SessaoPautaRepository sessaoPautaRepository) {
        this.sessaoPautaRepository = sessaoPautaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public SessaoPauta buscar(String id) {
        return this.sessaoPautaRepository.findById(id).orElse(null);
    }
}
