package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssociadoDTO;

import java.util.List;

public interface AssociadoService {

    AssociadoDTO salvarAssociado(AssociadoDTO associadoDTO) throws RuntimeException;

    AssociadoDTO buscarAssociadoPorId(Long id) throws RuntimeException;

    List<AssociadoDTO> buscarTodosOsAssociados() throws RuntimeException;

}
