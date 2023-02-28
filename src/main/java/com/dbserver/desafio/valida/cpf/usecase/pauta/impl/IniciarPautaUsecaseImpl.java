package com.dbserver.desafio.valida.cpf.usecase.pauta.impl;

import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;
import com.dbserver.desafio.valida.cpf.usecase.sessao.IniciarSessaoUsecase;
import com.dbserver.desafio.valida.cpf.exception.PautaInexistenteException;
import com.dbserver.desafio.valida.cpf.repository.PautaRepository;
import com.dbserver.desafio.valida.cpf.repository.entity.PautaEntity;
import com.dbserver.desafio.valida.cpf.repository.mapper.PautaEntityParaPautaMapper;
import com.dbserver.desafio.valida.cpf.usecase.pauta.SalvarPautaUsecase;
import com.dbserver.desafio.valida.cpf.usecase.pauta.IniciarPautaUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IniciarPautaUsecaseImpl implements IniciarPautaUsecase {

    private final PautaRepository pautaRepository;

    private final IniciarSessaoUsecase iniciarSessaoUsecase;

    private final SalvarPautaUsecase salvarPautaUsecase;

    @Override
    public Pauta execute(Integer idPauta, Integer duracao) {

        Optional<PautaEntity> pautaEntity = pautaRepository.findById(idPauta);

        if (pautaEntity.isEmpty()) {
            throw new PautaInexistenteException();
        }

        Pauta pauta = PautaEntityParaPautaMapper.INSTANCE.map(pautaEntity.get());

        pauta.setSessao(iniciarSessaoUsecase.execute(duracao));

        return salvarPautaUsecase.execute(pauta);
    }

}
