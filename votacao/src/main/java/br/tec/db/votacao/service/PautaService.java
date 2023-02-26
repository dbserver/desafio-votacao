package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.PautaDTO;

import java.util.List;

public interface PautaService {
    PautaDTO criarPauta(PautaDTO pautaDTO) throws RuntimeException;

    PautaDTO buscarPautaPorId(Long id) throws RuntimeException;

    List<PautaDTO> buscarTodasAsPautas() throws RuntimeException;

    List<PautaDTO> buscarPautasPorAssembleia(Long id) throws RuntimeException;
}