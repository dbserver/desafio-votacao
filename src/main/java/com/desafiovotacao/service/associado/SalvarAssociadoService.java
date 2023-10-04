package com.desafiovotacao.service.associado;

import com.desafiovotacao.domain.Associado;
import com.desafiovotacao.dto.AssociadoDTO;
import com.desafiovotacao.repository.AssociadoRepository;
import com.desafiovotacao.service.interfaces.ISalvarAssociadoService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class SalvarAssociadoService implements ISalvarAssociadoService {

    private final AssociadoRepository associadoRepository;

    public SalvarAssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    @Transactional
    public AssociadoDTO salvar(AssociadoDTO associadoDTO) {
        Associado associadoSalvo = this.associadoRepository.save(associadoDTO.toEntity());
        return AssociadoDTO.fromEntity(associadoSalvo);
    }
}
