package com.desafio.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.votacao.dto.request.VotacaoRequest;
import com.desafio.votacao.dto.response.MessageResponse;
import com.desafio.votacao.service.VotacaoService;

@RestController
@RequestMapping("/votacao")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VotacaoController {

	private VotacaoService votacaoService;

	@Autowired
	public VotacaoController(VotacaoService votacaoService) {
		this.votacaoService = votacaoService;
	}
	

	@PostMapping("/iniciar-votacao")
	public ResponseEntity<MessageResponse> iniciarVotacao(@RequestBody VotacaoRequest request) {
		return this.votacaoService.iniciarVotacao(request);
	}
}
