
package com.desafiovotacao.service;

import com.desafiovotacao.domain.VotoAssociado;
import com.desafiovotacao.repository.VotoRepository;
import com.desafiovotacao.service.interfaces.IBuscarVotacaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BuscarVotacaoService implements IBuscarVotacaoService {

    private final VotoRepository votoRepository;

    public BuscarVotacaoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public VotoAssociado buscar(String associadoId, String sessaoId) {
        return this.votoRepository.findByAssociadoIdAndSessaoPautaId(associadoId, sessaoId).orElse(null);
    }
}
