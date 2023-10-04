
package com.desafiovotacao.service.votacao;

import com.desafiovotacao.domain.VotoAssociado;
import com.desafiovotacao.repository.VotoRepository;
import com.desafiovotacao.service.interfaces.IBuscarVotacaoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuscarVotacaoService implements IBuscarVotacaoService {

    private final VotoRepository votoRepository;

    public BuscarVotacaoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public VotoAssociado buscarPorAssociadoAndSessao(String associadoId, String sessaoId) {
        return this.votoRepository.findByAssociadoIdAndSessaoPautaId(associadoId, sessaoId).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VotoAssociado> buscarTodosPorPauta(String pautaId) {
        return this.votoRepository.findAllByPautaId(pautaId);
    }
}
