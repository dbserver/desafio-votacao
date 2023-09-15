package com.desafio.projeto_votacao.service.impl;

import com.desafio.projeto_votacao.dto.SessaoDto;
import com.desafio.projeto_votacao.entity.Pauta;
import com.desafio.projeto_votacao.entity.Sessao;
import com.desafio.projeto_votacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessaoServiceImpl implements SessaoService {

    @Override
    public Sessao abrirSessaoVotacao(Integer duracaoMinutos, Pauta pauta) {

        return null;
    }

    @Override
    public Pauta fecharSessaoVotacao(Sessao sessao) {

        return null;
    }

    @Override
    public List<SessaoDto> listarSessoes() {

        return null;
    }

    @Override
    public List<SessaoDto> listarSessoesPorPauta(Long id) {

        return null;
    }
}
