package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.SessaoDeVotacaoDTO;

import java.util.List;

public interface SessaoDeVotacaoService {
    SessaoDeVotacaoDTO criarSessaoDeVotacao(SessaoDeVotacaoDTO sessaoDeVotacaoDTO);

    SessaoDeVotacaoDTO buscarSessaoDeVotacaoPorId(Long id);

    List<SessaoDeVotacaoDTO> buscarTodasAsSessoesDeVotacao();

    SessaoDeVotacaoDTO buscarSessaoDeVotacaoPorPauta(Long id);
}
