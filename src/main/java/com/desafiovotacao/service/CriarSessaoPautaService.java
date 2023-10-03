package com.desafiovotacao.service;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.domain.SessaoPauta;
import com.desafiovotacao.dto.SessaoPautaDTO;
import com.desafiovotacao.repository.SessaoPautaRepository;
import com.desafiovotacao.service.interfaces.IBuscarPautaPorIdService;
import com.desafiovotacao.service.interfaces.ICriarSessaoPautaService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

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
        Pauta pauta = this.buscarPautaPorIdService.buscar(sessaoPautaDTO.getPautaId());

        SessaoPauta sessaoPauta = this.sessaoPautaRepository.save(sessaoPautaDTO.toEntity());
        sessaoPauta.setPauta(pauta);
        return SessaoPautaDTO.fromEntity(sessaoPauta);
    }
}
