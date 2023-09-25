package com.desafio.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.votacao.dto.response.AssociadoDTO;
import com.desafio.votacao.service.AssociadoService;

@RestController
@RequestMapping("/associado")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AssociadoController {
	
	private AssociadoService associadoService;

	@Autowired
	public AssociadoController(AssociadoService associadoService) {
		this.associadoService = associadoService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<AssociadoDTO> saveData(@RequestBody AssociadoDTO dto) {
		return this.associadoService.salvar(dto);
	}

}
