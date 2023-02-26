package br.tec.db.votacao.service;

import br.tec.db.votacao.dto.SessaoDeVotacaoDTO;

import java.util.List;

public interface SessaoDeVotacaoService {
    SessaoDeVotacaoDTO criarSessaoDeVotacao(SessaoDeVotacaoDTO sessaoDeVotacaoDTO) throws RuntimeException;

    SessaoDeVotacaoDTO buscarSessaoDeVotacaoPorId(Long id) throws RuntimeException;

    List<SessaoDeVotacaoDTO> buscarTodasAsSessoesDeVotacao() throws RuntimeException;

    SessaoDeVotacaoDTO buscarSessaoDeVotacaoPorPauta(Long id) throws RuntimeException;

    void encerrarSessaoDeVotacao(Long id) throws RuntimeException;
}
