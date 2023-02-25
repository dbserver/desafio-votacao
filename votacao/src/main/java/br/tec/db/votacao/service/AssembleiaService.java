package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssembleiaDTO;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface AssembleiaService {
    AssembleiaDTO criarAssembleia(AssembleiaDTO assembleiaDTO) throws RuntimeException;

    List<AssembleiaDTO> buscarTodasAssembleias() throws DataAccessException;

    AssembleiaDTO buscarAssembleiaPorId(Long id) throws RuntimeException;

    void finalizarAssembleia(Long assembleiaId) throws RuntimeException;
}
