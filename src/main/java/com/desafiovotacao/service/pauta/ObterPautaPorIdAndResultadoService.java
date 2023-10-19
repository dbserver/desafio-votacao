
package com.desafiovotacao.service.pauta;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.domain.VotoAssociado;
import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.dto.TipoVotoEnum;
import com.desafiovotacao.repository.PautaRepository;
import com.desafiovotacao.service.interfaces.IBuscarVotacaoService;
import com.desafiovotacao.service.interfaces.IObterPautaPorIdAndResultado;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ObterPautaPorIdAndResultadoService implements IObterPautaPorIdAndResultado {

    private final PautaRepository pautaRepository;
    private final IBuscarVotacaoService buscarVotacaoService;

    public ObterPautaPorIdAndResultadoService(
            PautaRepository pautaRepository,
            IBuscarVotacaoService BuscarVotacaoService
    ) {
        this.pautaRepository = pautaRepository;
        this.buscarVotacaoService = BuscarVotacaoService;
    }

    @Override
    @Transactional
    public PautaDTO buscar(String pautaId) throws Exception {
        Pauta pauta = this.pautaRepository.findById(pautaId).orElse(null);

        if (pauta == null) {
            throw new Exception("Pauta não encontrada.");
        }

        if(!pauta.possuiSessoesAtivas() && !pauta.isResultadoVerificado()) {
            List<VotoAssociado> votos = this.buscarVotacaoService.buscarTodosPorPauta(pautaId);
            long votosAFavor = 0L;
            long votosContra = 0L;

            for (VotoAssociado voto: votos) {
                if(voto.getTipo().equals(TipoVotoEnum.SIM)) {
                    votosAFavor++;
                } else {
                    votosContra++;
                }
            }

            pauta.setVotosAFavor(votosAFavor);
            pauta.setVotosContra(votosContra);
            pauta.setResultadoVerificado(true);
            this.pautaRepository.save(pauta);
        }

        return PautaDTO.fromEntity(pauta);
    }
}
