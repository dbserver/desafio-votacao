package com.db.desafio.service;


import com.db.desafio.entity.Sessao;
import com.db.desafio.exception.SessaoException;
import com.db.desafio.repository.SessaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@AllArgsConstructor
@Service
public class SessaoService {

    private SessaoRepository sessaoRepository;
    private PautaService pautaService;

    private static final LocalDateTime ENCERRA_SESSAO = LocalDateTime.now();

    public void abrirSessao(Long pautaId) {
        var sessao = new Sessao(pautaService.obterPautaPorId(pautaId));
         sessaoRepository.save(sessao);
    }

    public void isAberta(Long id){
        Sessao sessao = sessaoRepository.findById(id).
                orElseThrow(() -> new SessaoException("Sessao inexistente"));
        if (ENCERRA_SESSAO.isAfter(sessao.getFinalSessao())){
         throw new SessaoException("Sessão já está encerrada");
        }
    }
}
