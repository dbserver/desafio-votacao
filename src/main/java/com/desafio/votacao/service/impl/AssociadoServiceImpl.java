package com.desafio.votacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.AssociadoDTO;
import com.desafio.votacao.entity.Associado;
import com.desafio.votacao.repository.AssociadoRepository;
import com.desafio.votacao.service.AssociadoService;

@Service
public class AssociadoServiceImpl implements AssociadoService {
	
private AssociadoRepository associadoRepository;
	
	@Autowired
	public AssociadoServiceImpl(AssociadoRepository associadoRepository) {
		this.associadoRepository = associadoRepository;
	}

	@Override
	public void salvar(AssociadoDTO dto) {
		Associado associado = new Associado(dto);
		this.associadoRepository.save(associado);
	}
	
}
