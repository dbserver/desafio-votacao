package com.desafiovotacao.service;

import com.desafiovotacao.dto.AssociadoDTO;
import com.desafiovotacao.repository.AssociadoRepository;
import com.desafiovotacao.service.interfaces.IListarAssociadosService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ListarAssociadosService implements IListarAssociadosService {

    private final AssociadoRepository associadoRepository;

    public ListarAssociadosService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssociadoDTO> listar(Pageable page) {
        return this.associadoRepository.findAll(page).map(AssociadoDTO::fromEntity);
    }
}
