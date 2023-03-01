package com.dbserver.desafio.votacao.usecase.pauta.impl;

import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.sessao.IniciarSessaoUsecase;
import com.dbserver.desafio.votacao.exception.PautaInexistenteException;
import com.dbserver.desafio.votacao.repository.PautaRepository;
import com.dbserver.desafio.votacao.repository.entity.PautaEntity;
import com.dbserver.desafio.votacao.repository.mapper.PautaEntityParaPautaMapper;
import com.dbserver.desafio.votacao.usecase.pauta.SalvarPautaUsecase;
import com.dbserver.desafio.votacao.usecase.pauta.IniciarPautaUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IniciarPautaUsecaseImpl implements IniciarPautaUsecase {

    private final PautaRepository pautaRepository;

    private final IniciarSessaoUsecase iniciarSessaoUsecase;

    private final SalvarPautaUsecase salvarPautaUsecase;

    @Override
    public Pauta execute(Integer idPauta, Integer duracao) {

        log.info("[IniciarPautaUsecaseImpl] Chamada ao usecase para iniciar a Pauta, IdPauta: " + idPauta);

        Optional<PautaEntity> pautaEntity = pautaRepository.findById(idPauta);

        if (pautaEntity.isEmpty()) {
            throw new PautaInexistenteException();
        }

        Pauta pauta = PautaEntityParaPautaMapper.INSTANCE.map(pautaEntity.get());

        pauta.setSessao(iniciarSessaoUsecase.execute(duracao));

        return salvarPautaUsecase.execute(pauta);
    }

}
