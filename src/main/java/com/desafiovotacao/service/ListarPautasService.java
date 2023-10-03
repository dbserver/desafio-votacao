
package com.desafiovotacao.service;

import com.desafiovotacao.dto.PautaDTO;
import com.desafiovotacao.repository.PautaRepository;
import com.desafiovotacao.service.interfaces.IListarPautaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ListarPautasService implements IListarPautaService {

    private final PautaRepository pautaRepository;

    public ListarPautasService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PautaDTO> listar(Pageable page) {
        return this.pautaRepository.findAll(page).map(PautaDTO::fromEntity);
    }
}
