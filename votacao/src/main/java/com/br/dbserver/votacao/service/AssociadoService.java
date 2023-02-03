package com.br.dbserver.votacao.service;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.dbserver.votacao.controller.dto.DadosAbrirSessao;
import com.br.dbserver.votacao.dto.DadosCadastroPauta;
import com.br.dbserver.votacao.model.Associado;
import com.br.dbserver.votacao.model.Pauta;
import com.br.dbserver.votacao.repository.AssociadoRepository;
import com.br.dbserver.votacao.repository.PautaRepository;

import jakarta.validation.Valid;

@Service
public class AssociadoService {
	
	@Autowired
	private AssociadoRepository associadoRepository;
	
	public Associado findAssociadoById(long id) {
		  return  associadoRepository.getReferenceById(id);
 
	}

	
			

}
