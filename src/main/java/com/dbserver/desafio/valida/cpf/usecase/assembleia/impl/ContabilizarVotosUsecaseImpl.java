package com.dbserver.desafio.valida.cpf.usecase.assembleia.impl;

import com.dbserver.desafio.valida.cpf.exception.PautaInexistenteException;
import com.dbserver.desafio.valida.cpf.exception.PautaSemVotoException;
import com.dbserver.desafio.valida.cpf.repository.PautaRepository;
import com.dbserver.desafio.valida.cpf.repository.VotoRepository;
import com.dbserver.desafio.valida.cpf.repository.entity.PautaEntity;
import com.dbserver.desafio.valida.cpf.repository.entity.VotoEntity;
import com.dbserver.desafio.valida.cpf.repository.mapper.PautaEntityParaPautaMapper;
import com.dbserver.desafio.valida.cpf.repository.mapper.VotoEntityParaVotoMapper;
import com.dbserver.desafio.valida.cpf.usecase.domain.Pauta;
import com.dbserver.desafio.valida.cpf.usecase.domain.Voto;
import com.dbserver.desafio.valida.cpf.usecase.domain.VotosPauta;
import com.dbserver.desafio.valida.cpf.usecase.enuns.VotoEnum;
import com.dbserver.desafio.valida.cpf.usecase.assembleia.ContabilizarVotosUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContabilizarVotosUsecaseImpl implements ContabilizarVotosUsecase {

    private final PautaRepository pautaRepository;

    private final VotoRepository votoRepository;

    @Override
    public VotosPauta execute(Integer idPauta) {

        Optional<PautaEntity> pautaEntity = pautaRepository.findById(idPauta);

        if (pautaEntity.isEmpty()) {
            throw new PautaInexistenteException();
        }

        List<VotoEntity> votoEntityList = votoRepository.findByPauta(pautaEntity.get());

        verificaPautaSemVotos(votoEntityList);

        List<Voto> votoList = VotoEntityParaVotoMapper.INSTANCE.mapList(votoEntityList);

        Pauta pauta = PautaEntityParaPautaMapper.INSTANCE.map(pautaEntity.get());

        return criarVotosPauta(pauta, votoList);
    }

    private static void verificaPautaSemVotos(List<VotoEntity> votoEntityList) {

        if(votoEntityList.isEmpty()){
            throw new PautaSemVotoException();
        }
    }

    private static VotosPauta criarVotosPauta(Pauta pauta, List<Voto> votoList) {

        List<Voto> votoSimList = votoList.stream()
                .filter(voto -> voto.getVoto().equals(VotoEnum.SIM)).collect(Collectors.toList());
        List<Voto> votoNaoList = votoList.stream()
                .filter(voto -> voto.getVoto().equals(VotoEnum.NAO)).collect(Collectors.toList());

        return VotosPauta.builder()
                .pauta(pauta)
                .totalVotosSim(votoSimList.size())
                .totalVotosNao(votoNaoList.size())
                .build();
    }
}
