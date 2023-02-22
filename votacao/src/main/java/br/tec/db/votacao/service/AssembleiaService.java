package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssembleiaDTO;

import java.util.List;

public interface AssembleiaService {
    AssembleiaDTO criarAssembleia(AssembleiaDTO assembleiaDTO);

    List<AssembleiaDTO> buscarTodasAssembleias();

    AssembleiaDTO buscarAssembleiaPorId(Long id);
}
