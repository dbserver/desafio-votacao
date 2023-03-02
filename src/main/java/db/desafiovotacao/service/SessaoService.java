package db.desafiovotacao.service;


import db.desafiovotacao.model.Sessao;
import db.desafiovotacao.repository.SessaoRepository;
import db.desafiovotacao.service.interfaces.ISessaoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class SessaoService implements ISessaoService {

    private final SessaoRepository sessaoRepository;
    
    public SessaoService(SessaoRepository sessaoRepository){
        this.sessaoRepository = sessaoRepository;
    }

    @Override
    public Sessao criarSessao(Sessao sessao) {

        LocalDateTime duracaoPadrao = sessao.getInicioSessao().plusMinutes(1);
        LocalDateTime inicioSessao = sessao.getInicioSessao();
        LocalDateTime finalSessao = sessao.getFinalSessao();

        if(sessao.getFinalSessao() == null || (finalSessao.isAfter(inicioSessao) && finalSessao.isBefore(duracaoPadrao)))
            sessao.setFinalSessao(sessao.getInicioSessao().plusMinutes(1));

        return sessaoRepository.save(sessao);
    }
}
