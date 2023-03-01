package com.dbserver.desafio.votacao.usecase.pauta.impl;

import com.dbserver.desafio.votacao.repository.entity.PautaEntity;
import com.dbserver.desafio.votacao.repository.mapper.PautaEntityParaPautaMapper;
import com.dbserver.desafio.votacao.repository.mapper.PautaParaPautaEntityMapper;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase;
import com.dbserver.desafio.votacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class SalvarPautaUsecaseImpl implements SalvarPautaUsecase {
    private final PautaRepository pautaRepository;
    public Pauta execute(Pauta pauta) {

        PautaEntity pautaEntity = PautaParaPautaEntityMapper.INSTANCE.map(pauta);

        PautaEntity pautaEntityPersistida = pautaRepository.save(pautaEntity);

        return PautaEntityParaPautaMapper.INSTANCE.map(pautaEntityPersistida);
    }
}
