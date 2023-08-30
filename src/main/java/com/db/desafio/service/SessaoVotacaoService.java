package com.db.desafio.service;


import com.db.desafio.dto.VotoDto;
import com.db.desafio.entity.SessaoVotacao;
import com.db.desafio.entity.Voto;
import com.db.desafio.exception.SessaoVotacaoException;
import com.db.desafio.repository.SessaoVotacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Service
public class SessaoVotacaoService {

    private SessaoVotacaoRepository sessaoVotacaoRepository;
    private PautaService pautaService;
    private AssociadoService associadoService;


    public void abrirSessao(Long pautaId) {
        var sessao = new SessaoVotacao(pautaService.obterPautaPorId(pautaId));
        sessaoVotacaoRepository.save(sessao);
    }

    public SessaoVotacao obterSessao(Long id) {
        return sessaoVotacaoRepository.findById(id)
                .orElseThrow(() -> new SessaoVotacaoException("Sessao inexistente"));
    }

    public List<SessaoVotacao> obterSessoes() {
        return sessaoVotacaoRepository.findAll();
    }


    public SessaoVotacao retonarSesaoAberta(Long id) {
        LocalDateTime encerrarSessao = LocalDateTime.now();
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(id).
                orElseThrow(() -> new SessaoVotacaoException("Sessao inexistente"));

        if (encerrarSessao.isAfter(sessaoVotacao.getFinalSessao())) {
            throw new SessaoVotacaoException("Sessão já está encerrada");
        }
        return sessaoVotacao;
    }


    public void votarSessao(Long sessaoId, VotoDto votoDto) {
        SessaoVotacao sessaoVotacao = retonarSesaoAberta(sessaoId);
        verificarVotoExistente(sessaoVotacao, votoDto.getCpf());
        var voto = new Voto(votoDto.getVotoEnum(),
                associadoService.obterAssociadoPorCpf(votoDto.getCpf()));
        sessaoVotacao.getVotos().add(voto);
        sessaoVotacaoRepository.save(sessaoVotacao);
    }

    private void verificarVotoExistente(SessaoVotacao sessaoVotacao, String cpf) {
        if (sessaoVotacao.getVotos().stream().anyMatch(voto -> voto.getAssociado().getCpf().equals(cpf))) {
            throw new SessaoVotacaoException("O associado já votou.");
        }
    }


}
