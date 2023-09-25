package com.desafio.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.votacao.dto.request.VotoRequest;
import com.desafio.votacao.dto.response.MessageResponse;
import com.desafio.votacao.service.VotoService;

@RestController
@RequestMapping("/voto")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VotoController {

	private VotoService votoService;

	@Autowired
	public VotoController(VotoService votoService) {
		this.votoService = votoService;
	}

	@PostMapping("/votar")
	public ResponseEntity<MessageResponse> votar(@RequestBody VotoRequest request) {
		return this.votoService.salvar(request);
	}
	
}
