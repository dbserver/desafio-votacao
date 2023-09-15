package com.desafio.projeto_votacao.service.impl;

import com.desafio.projeto_votacao.dto.PautaDto;
import com.desafio.projeto_votacao.dto.ResultadoVotacaoDto;
import com.desafio.projeto_votacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {

    @Override
    public void cadastrarPauta(String titulo, String descricao, Integer tempoSessao) {

    }

    private ResultadoVotacaoDto enviarResultadoVotacao(Long pautaId) {

        return null;
    }


    @Override
    public List<PautaDto> listarPautas() {

        return null;
    }

    @Override
    public ResultadoVotacaoDto obterResultadoVotacao(Long pautaId) {

        return null;
    }
}