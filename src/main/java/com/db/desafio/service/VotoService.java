package com.db.desafio.service;



import com.db.desafio.dto.VotoDto;
import com.db.desafio.entity.Sessao;
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

    public void salvarVoto(Long sessaoId, VotoDto votoDto) {
        Sessao sessao = sessaoService.retonarSesaoAberta(sessaoId);
        Voto voto = new Voto(votoDto.getVotoEnum()
                ,pautaService.obterPautaPorId(sessao.getPauta().getId())
                ,associadoService.obterAssociadoPorCpf(votoDto.getAssociadoDto().getCpf()));
        isVotou(sessaoId, voto.getAssociado().getId());
        votoRepository.save(voto);
    }

    private void isVotou(Long pautaId, Long associadoId) {
        boolean associadoJaVotou = votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId);
        if (associadoJaVotou) {
            throw new VotoException("O associado já votou nesta Pauta.");
        }
    }
}

