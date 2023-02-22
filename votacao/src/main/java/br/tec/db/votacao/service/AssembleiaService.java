package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssembleiaDTO;

import java.util.List;

public interface AssembleiaService {
    AssembleiaDTO criar(AssembleiaDTO assembleiaDTO);

    List<AssembleiaDTO> listar();
}
