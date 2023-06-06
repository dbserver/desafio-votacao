package com.example.votacaodesafio.service;

import com.example.votacaodesafio.domain.dto.VotacaoDTO;
import com.example.votacaodesafio.domain.dto.VotacaoResponse;
import com.example.votacaodesafio.domain.entity.Assosciado;
import com.example.votacaodesafio.domain.entity.SessaoVotacao;
import com.example.votacaodesafio.domain.entity.Votacao;
import com.example.votacaodesafio.repository.AssociadoRepository;
import com.example.votacaodesafio.repository.SessaoVotacaoRepository;
import com.example.votacaodesafio.repository.VotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotacaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private VotacaoRepository votacaoRepository;
    public VotacaoResponse votar(Long idAssosciado, VotacaoDTO votacaoDTO) {
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(votacaoDTO.getSessaoVotacao().getId()).orElseThrow(() -> new RuntimeException("Não Encontrado"));
        Assosciado assosciado = associadoRepository.findById(idAssosciado).orElseThrow(() -> new RuntimeException("Não Encontrado"));

        if (sessaoVotacao.ehSessaoFechada()) {
            throw new RuntimeException("Sessão encerrada");
        }

        boolean jaVotou = votacaoRepository.existsByVotingSessionIdAndAssociateId(votacaoDTO.getSessaoVotacao().getId(), idAssosciado);

        if (jaVotou) {
            throw new RuntimeException("Esse usuario já votou");
        }

        Votacao vote = new Votacao();
        vote.setVote(votacaoDTO.getVote());
        vote.setAssosciado(assosciado);
        vote.setSessaoVotacao(sessaoVotacao);
        votacaoRepository.save(vote);

        return new VotacaoResponse(vote.getVote());
    }
}