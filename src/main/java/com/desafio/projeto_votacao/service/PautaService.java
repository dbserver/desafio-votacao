package com.desafio.projeto_votacao.service;

import com.desafio.projeto_votacao.dto.PautaDto;
import com.desafio.projeto_votacao.dto.PautaRequestDto;
import com.desafio.projeto_votacao.dto.ResultadoVotacaoDto;
import java.util.List;

public interface PautaService {

    void cadastrarPauta(PautaRequestDto request);
    ResultadoVotacaoDto obterResultadoVotacao(Long pautaId);
    List<PautaDto> listarPautas();
}
