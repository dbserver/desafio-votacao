package com.db.desafio.service;



import com.db.desafio.entity.Voto;
import com.db.desafio.exception.VotoException;
import com.db.desafio.repository.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VotoService {

    private VotoRepository votoRepository;
    private PautaService pautaService;
    private SessaoService sessaoService;
    private AssociadoService associadoService;

    public void salvarVoto(Long sessaoId, Long pautaId, Voto votoRequest) {
        sessaoService.isAberta(sessaoId);
        Voto voto = new Voto(votoRequest.getVotoEnum()
                ,pautaService.obterPautaPorId(pautaId)
                ,associadoService.obterAssociadoPorCpf(votoRequest.getAssociado().getCpf()));
        isVotou(sessaoId, votoRequest.getAssociado().getId());
        votoRepository.save(voto);
    }

    private void isVotou(Long pautaId, Long associadoId) {
        boolean associadoJaVotou = votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId);
        if (associadoJaVotou) {
            throw new VotoException("O associado já votou nesta Pauta.");
        }
    }
}

