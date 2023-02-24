package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.VotoDTO;

public interface VotoService {
    VotoDTO criarVoto(VotoDTO votoDTO);

    Long calcularVotosSimPorSessaoDeVotacao(Long id);

    Long calcularVotosNaoPorSessaoDeVotacao(Long id);
}
