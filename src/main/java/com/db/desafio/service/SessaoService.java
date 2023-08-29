package com.db.desafio.service;


import com.db.desafio.entity.Sessao;
import com.db.desafio.exception.SessaoException;
import com.db.desafio.repository.SessaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@AllArgsConstructor
@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;
    private PautaService pautaService;

    private static final LocalDateTime ENCERRA_SESSAO = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    public void abrirSessao(Long pautaId) {
        var sessao = new Sessao(pautaService.obterPautaPorId(pautaId));
         sessaoRepository.save(sessao);
    }
    public Sessao obterSessao(Long id){
        return sessaoRepository.findById(id)
                .orElseThrow(() -> new SessaoException("Sessao inexistente"));
    }
    public List<Sessao> obterSessoes() {
        return sessaoRepository.findAll();
    }

    public Sessao retonarSesaoAberta(Long id){
        Sessao sessao = sessaoRepository.findById(id).
                orElseThrow(() -> new SessaoException("Sessao inexistente"));

        if (ENCERRA_SESSAO.isAfter(sessao.getFinalSessao())){
         throw new SessaoException("Sessão já está encerrada");
        }
        return sessao;
    }

}
