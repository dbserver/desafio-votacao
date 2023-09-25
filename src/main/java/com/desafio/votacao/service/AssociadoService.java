package com.desafio.votacao.service;

import org.springframework.http.ResponseEntity;

import com.desafio.votacao.dto.response.AssociadoDTO;
import com.desafio.votacao.entity.Associado;

public interface AssociadoService {

	public ResponseEntity<AssociadoDTO> salvar(AssociadoDTO dto);

	public Associado buscarPorCPF(String cpf);
	
}
