package com.desafio.projeto_votacao.service;

import com.desafio.projeto_votacao.dto.PautaDto;
import com.desafio.projeto_votacao.dto.ResultadoVotacaoDto;
import java.util.List;

public interface PautaService {

    void cadastrarPauta(String titulo, String descricao, Integer tempoSessao);
    ResultadoVotacaoDto obterResultadoVotacao(Long pautaId);
    List<PautaDto> listarPautas();
}
