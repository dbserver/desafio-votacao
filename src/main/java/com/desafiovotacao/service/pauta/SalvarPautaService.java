package com.desafiovotacao.service.pauta;

import com.desafiovotacao.domain.Pauta;
import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.repository.PautaRepository;
import com.desafiovotacao.service.interfaces.ISalvarPautaService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SalvarPautaService implements ISalvarPautaService {

    private final PautaRepository pautaRepository;

    public SalvarPautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    @Transactional
    public PautaDTO salvar(PautaDTO pautaDTO) {
        Pauta pauta = this.pautaRepository.save(pautaDTO.toEntity());
        return PautaDTO.fromEntity(pauta);
    }
}
