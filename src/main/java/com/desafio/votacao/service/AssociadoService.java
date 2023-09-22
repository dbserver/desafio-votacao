package com.desafio.votacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.votacao.repository.AssociadoRepository;

@Service
public class AssociadoService {

	private AssociadoRepository associadoRepository;
	
	@Autowired
	public AssociadoService(AssociadoRepository associadoRepository) {
		this.associadoRepository = associadoRepository;
	}
	
	
}
