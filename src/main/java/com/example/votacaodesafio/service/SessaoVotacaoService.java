package com.example.votacaodesafio.service;

import com.example.votacaodesafio.domain.dto.VotacaoResultadoResponse;
import com.example.votacaodesafio.domain.entity.SessaoVotacao;
import com.example.votacaodesafio.domain.enums.VotoEnum;
import com.example.votacaodesafio.repository.VotacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@AllArgsConstructor
public class SessaoVotacaoService extends GenericServiceImpl<SessaoVotacao, Long> implements GenericService<SessaoVotacao, Long>{

    private VotacaoRepository votacaoRepository;

    public VotacaoResultadoResponse getResult(Long id) {
        SessaoVotacao sessaoVotacao = findById(id);
        if (!sessaoVotacao.ehSessaoFechada()) {
            throw new RuntimeException("Sessão Fechada");
        }
        HashMap<String, Long> resultadoMap = votacaoRepository.countByVotingSessionIdAndVote(id);
        Long sim = resultadoMap.get(VotoEnum.YES.name());
        Long nao = resultadoMap.get(VotoEnum.NO.name());
        return VotacaoResultadoResponse.builder().sim(sim).nao(nao).total(sim + nao).build();
    }
}