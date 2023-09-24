package com.desafio.votacao.service;

import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.AssociadoDTO;

@Service
public interface AssociadoService {

	public void salvar(AssociadoDTO dto);
	
}
