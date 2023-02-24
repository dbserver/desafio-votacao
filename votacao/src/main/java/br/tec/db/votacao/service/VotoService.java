package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.VotoDTO;

import java.util.List;

public interface VotoService {
    VotoDTO votar(VotoDTO votoDTO);

    VotoDTO buscarVotoPorId(Long id);

    List<VotoDTO> buscarTodosOsVotos();

    List<VotoDTO> buscarVotosPorSessaoDeVotacao(Long id);
}
