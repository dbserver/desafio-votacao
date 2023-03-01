package com.dbserver.desafio.votacao.usecase.assembleia.impl;

import com.dbserver.desafio.votacao.exception.PautaInexistenteException;
import com.dbserver.desafio.votacao.exception.PautaSemSessaoException;
import com.dbserver.desafio.votacao.exception.SessaoFinalizadaException;
import com.dbserver.desafio.votacao.exception.VotoJaRealizadoException;
import com.dbserver.desafio.votacao.repository.PautaRepository;
import com.dbserver.desafio.votacao.repository.VotoRepository;
import com.dbserver.desafio.votacao.repository.entity.PautaEntity;
import com.dbserver.desafio.votacao.repository.entity.VotoEntity;
import com.dbserver.desafio.votacao.repository.mapper.PautaEntityParaPautaMapper;
import com.dbserver.desafio.votacao.repository.mapper.VotoEntityParaVotoMapper;
import com.dbserver.desafio.votacao.repository.mapper.VotoParaVotoEntityMapper;
import com.dbserver.desafio.votacao.usecase.domain.Pauta;
import com.dbserver.desafio.votacao.usecase.domain.Sessao;
import com.dbserver.desafio.votacao.usecase.domain.Voto;
import com.dbserver.desafio.votacao.usecase.assembleia.ReceberVotoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceberVotoUseCaseImpl implements ReceberVotoUseCase {

    private final Clock clock;

    private final PautaRepository pautaRepository;

    private final VotoRepository votoRepository;

    @Override
    public Voto execute(Voto voto) {

        Optional<PautaEntity> pautaEntity = pautaRepository.findById(voto.getPauta().getIdPauta());

        if (pautaEntity.isEmpty()) {
            throw new PautaInexistenteException();
        }

        Pauta pauta = PautaEntityParaPautaMapper.INSTANCE.map(pautaEntity.get());

        voto.setPauta(pauta);
        Sessao sessao = pauta.getSessao();

        if (sessao == null) {
            throw new PautaSemSessaoException();
        }

        verificarSessaoFechada(sessao);

        verificarSeUsuarioJaVotou(voto);

        VotoEntity votoEntity = votoRepository.save(VotoParaVotoEntityMapper.INSTANCE.map(voto));

        return VotoEntityParaVotoMapper.INSTANCE.map(votoEntity);
    }

    private void verificarSessaoFechada(Sessao sessao) {

        LocalDateTime dataHoraMinutoLimiteSessao = sessao.getInicio().plusMinutes(sessao.getDuracao());
        LocalDateTime dataHoraMinutoAtual = LocalDateTime.now(clock);

        if (dataHoraMinutoAtual.isAfter(dataHoraMinutoLimiteSessao)) {
            throw new SessaoFinalizadaException();
        }
    }

    private void verificarSeUsuarioJaVotou(Voto voto) {

        List<VotoEntity> votoPorCpfLista = votoRepository.findByCpfAssociado(voto.getCpfAssociado());

        if (!votoPorCpfLista.isEmpty()) {
            throw new VotoJaRealizadoException();
        }
    }

}
