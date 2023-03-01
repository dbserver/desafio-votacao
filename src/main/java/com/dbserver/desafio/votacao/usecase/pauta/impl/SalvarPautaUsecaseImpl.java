package com.dbserver.desafio.votacao.usecase.pauta.impl;

import com.dbserver.desafio.votacao.repository.entity.PautaEntity;
import com.dbserver.desafio.votacao.repository.mapper.PautaEntityParaPautaMapper;
import com.dbserver.desafio.votacao.repository.mapper.PautaParaPautaEntityMapper;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase;
import com.dbserver.desafio.votacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SalvarPautaUsecaseImpl implements SalvarPautaUsecase {
    private final PautaRepository pautaRepository;
    public Pauta execute(Pauta pauta) {

        log.info("[SalvarPautaUsecaseImpl] Persistir Pauta");

        PautaEntity pautaEntity = PautaParaPautaEntityMapper.INSTANCE.map(pauta);

        PautaEntity pautaEntityPersistida = pautaRepository.save(pautaEntity);

        return PautaEntityParaPautaMapper.INSTANCE.map(pautaEntityPersistida);
    }
}
