package com.db.api.services;

import com.db.api.dtos.response.SessaoResponse;
import com.db.api.enums.ResultadoSessao;
import com.db.api.enums.VotoEnum;
import com.db.api.exceptions.RegistroNaoEncontradoException;
import com.db.api.models.Pauta;
import com.db.api.models.Sessao;
import com.db.api.repositories.PautaRepository;
import com.db.api.repositories.SessaoRepository;
import com.db.api.repositories.VotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;

    @Transactional
    public Sessao iniciarSessaoVotacao(String pautaTitulo, LocalDateTime dataEncerramento) {
        Pauta pauta = buscarPauta(pautaTitulo);
        Sessao sessao = criarSessao(pauta, dataEncerramento);

        return sessaoRepository.save(sessao);
    }

    private Pauta buscarPauta(String pautaTitulo) {
        return pautaRepository.findByTitulo(pautaTitulo).orElseThrow(() -> new RegistroNaoEncontradoException("A pauta requerida não foi encontrado!"));
    }

    private Sessao criarSessao(Pauta pauta, LocalDateTime dataEncerramento) {
        Sessao sessao = new Sessao(pauta);

        if (dataEncerramento != null) {
            sessao.setDataEncerramento(dataEncerramento);
        }

        return sessao;
    }

}

