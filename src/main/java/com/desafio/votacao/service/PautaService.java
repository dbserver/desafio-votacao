package com.desafio.votacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.desafio.votacao.dto.response.PautaDTO;
import com.desafio.votacao.entity.Pauta;

public interface PautaService {
	
	public ResponseEntity<PautaDTO> salvar(PautaDTO dto);
	
	public Optional<Pauta> buscarPorId(Long pautaId);

	/**
	 * Método utilizado para Registrar a quantidade de Votos em uma Pauta e desativar uma Pauta após fechamento de votação.
	 * @param votosSim quantidade de votos sim na Pauta  registro do tipo Long
	 * @param votosNao quantidade de votos não na Pauta  registro do tipo Long
	 * @param pauta Objeto de Pauta
	 */
	public void salvarVotos(Long votosSim, Long votosNao, Pauta pauta);  
	
	/**
	 * Método utilizado para Atualizar o Status da Pauta após o fechamento de uma votação.
	 * @param pautaId
	 */
	public void atualizarPautaVotacao(Long pautaId); 
	

	public List<Pauta> listarPautaAguardandoApuracao();
}
