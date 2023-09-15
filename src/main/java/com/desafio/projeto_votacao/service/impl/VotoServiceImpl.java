package com.desafio.projeto_votacao.service.impl;

import com.desafio.projeto_votacao.dto.VotoDto;
import com.desafio.projeto_votacao.entity.Pauta;
import com.desafio.projeto_votacao.enums.VotoEnum;
import com.desafio.projeto_votacao.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VotoServiceImpl implements VotoService {

    @Override
    public void registrarVoto(VotoEnum votoEnum, String cpfAssociado) {

    }

    @Override
    public List<VotoDto> obterResultadoVotacao(Pauta pauta) {

        return null;
    }
}
