package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.PautaDTO;

import java.util.List;

public interface PautaService {
    PautaDTO criarPauta(PautaDTO pautaDTO);

    PautaDTO buscarPautaPorId(Long id);

    List<PautaDTO> buscarTodasAsPautas();

    List<PautaDTO> buscarPautasPorAssembleia(Long id);
}