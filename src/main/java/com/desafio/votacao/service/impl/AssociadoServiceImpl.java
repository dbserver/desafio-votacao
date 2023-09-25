package com.desafio.votacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.votacao.dto.response.AssociadoDTO;
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
	public ResponseEntity<AssociadoDTO> salvar(AssociadoDTO dto) {
		Associado associado = new Associado(dto);
		AssociadoDTO retornoDTO = new AssociadoDTO(this.associadoRepository.save(associado));
		return ResponseEntity.ok(retornoDTO);
	}

	@Override
	public Associado buscarPorCPF(String cpf) {
		return this.associadoRepository.findFirstByCpf(cpf);
	}
	
}
