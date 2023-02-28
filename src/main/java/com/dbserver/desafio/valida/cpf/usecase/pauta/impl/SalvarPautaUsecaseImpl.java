package com.dbserver.desafio.valida.cpf.usecase.pauta.impl;

import com.dbserver.desafio.valida.cpf.repository.entity.PautaEntity;
import com.dbserver.desafio.valida.cpf.repository.mapper.PautaEntityParaPautaMapper;
import com.dbserver.desafio.valida.cpf.repository.mapper.PautaParaPautaEntityMapper;
import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;
import com.dbserver.desafio.valida.cpf.usecase.pauta.SalvarPautaUsecase;
import com.dbserver.desafio.valida.cpf.repository.PautaRepository;
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
