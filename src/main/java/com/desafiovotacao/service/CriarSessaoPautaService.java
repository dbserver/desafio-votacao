package com.desafiovotacao.service;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.domain.SessaoPauta;
import com.desafiovotacao.dto.SessaoPautaDTO;
import com.desafiovotacao.repository.SessaoPautaRepository;
import com.desafiovotacao.service.interfaces.IBuscarPautaPorIdService;
import com.desafiovotacao.service.interfaces.ICriarSessaoPautaService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CriarSessaoPautaService implements ICriarSessaoPautaService {

    private final SessaoPautaRepository sessaoPautaRepository;
    private final IBuscarPautaPorIdService buscarPautaPorIdService;

    public CriarSessaoPautaService(
            IBuscarPautaPorIdService buscarPautaPorIdService,
            SessaoPautaRepository sessaoPautaRepository
    ) {
        this.sessaoPautaRepository = sessaoPautaRepository;
        this.buscarPautaPorIdService = buscarPautaPorIdService;
    }

    @Override
    @Transactional
    public SessaoPautaDTO criar(SessaoPautaDTO sessaoPautaDTO) {
        long duracaoSessao = sessaoPautaDTO.getDuracaoMinutos();

        Pauta pauta = this.buscarPautaPorIdService.buscar(sessaoPautaDTO.getPautaId());

        SessaoPauta sessaoPauta = sessaoPautaDTO.toEntity();
        sessaoPauta.setPauta(pauta);

        if(duracaoSessao == 0) {
            duracaoSessao = 1L;
        }

        sessaoPauta.setDataInicio(LocalDateTime.now());
        sessaoPauta.setDataFim(sessaoPauta.getDataInicio().plusMinutes(duracaoSessao));
        sessaoPauta.setDuracaoMinutos(duracaoSessao);

        SessaoPauta sessaoPautaCriada = this.sessaoPautaRepository.save(sessaoPauta);
        return SessaoPautaDTO.fromEntity(sessaoPautaCriada);
    }
}
