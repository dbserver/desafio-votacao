package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.AssociadoDTO;

import java.util.List;

public interface AssociadoService {

    AssociadoDTO salvarAssociado(AssociadoDTO associadoDTO);

    AssociadoDTO buscarAssociadoPorId(Long id);

    List<AssociadoDTO> buscarTodosOsAssociados();

    List<AssociadoDTO> buscarAssociadosPorAssembleia(Long id);
}
