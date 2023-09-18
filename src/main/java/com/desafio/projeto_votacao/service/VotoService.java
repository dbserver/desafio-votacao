package com.desafio.projeto_votacao.service;

import com.desafio.projeto_votacao.dto.VotoDto;
import com.desafio.projeto_votacao.dto.VotoRequestDto;
import com.desafio.projeto_votacao.entity.Pauta;
import com.desafio.projeto_votacao.enums.VotoEnum;
import java.util.List;

public interface VotoService {

    void registrarVoto(VotoEnum voto , VotoRequestDto request);

    List<VotoDto> obterResultadoVotacao(Pauta pauta);

}