package com.desafio.votacao.service;

import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.PautaDTO;

@Service
public interface PautaService {
	
	public void salvar(PautaDTO dto);

	/**
	 * Método utilizado para Registrar a quantidade de Votos em uma Pauta e desativar uma Pauta após fechamento de votação.
	 * @param votosSim quantidade de votos sim na Pauta  registro do tipo Long
	 * @param votosNao quantidade de votos não na Pauta  registro do tipo Long
	 * @param pautaId  id de Pauta do tipo Long
	 */
	public void salvarVotos(Long votosSim, Long votosNao, Long pautaId); 
	
}
