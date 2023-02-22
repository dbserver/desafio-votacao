package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.PautaDTO;

import java.util.List;

public interface PautaService {
    PautaDTO criar(PautaDTO pautaDTO);

    List<PautaDTO> listar();

    List<PautaDTO> listarPorAssembleia(Long id);
}