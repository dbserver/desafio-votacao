package com.desafio.projeto_votacao.service;

import com.desafio.projeto_votacao.dto.SessaoDto;
import com.desafio.projeto_votacao.entity.Pauta;
import com.desafio.projeto_votacao.entity.Sessao;
import java.util.List;

public interface SessaoService {

    Sessao abrirSessaoVotacao(Integer duracaoMinutos, Pauta pauta);

    Pauta fecharSessaoVotacao(Sessao sessao);

    List<SessaoDto> listarSessoes();

    List<SessaoDto> listarSessoesPorPauta(Long id);

}
