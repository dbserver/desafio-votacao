package com.db.votacao.api.service;

import com.db.votacao.api.interfaces.ISessaoService;
import com.db.votacao.api.model.Sessao;
import com.db.votacao.api.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessaoService implements ISessaoService {

    private final SessaoRepository sessaoRepository;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository) {
        this.sessaoRepository = sessaoRepository;
    }

    @Override
    public Sessao criarSessao(Sessao sessao) {

        LocalDateTime duracaoSessao = sessao.getInicioSessao().plusSeconds(1);
        LocalDateTime inicioSessao = sessao.getInicioSessao();
        LocalDateTime finalSessao = sessao.getFinalSessao();

        if (sessao.getFinalSessao() == null || (finalSessao.isAfter(inicioSessao) && finalSessao.isBefore(duracaoSessao)))
            sessao.setFinalSessao(sessao.getInicioSessao().plusSeconds(1));

        return sessaoRepository.save(sessao);
    }
}
