package com.cooperativa.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperativa.votacao.dto.AberturaSessaoDTO;
import com.cooperativa.votacao.service.SessaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/aberturaSessao")
public class SessaoController {
	
	
	@Autowired
	private SessaoService sessaoService;

	
	@PatchMapping
	public ResponseEntity<Void> abrirSessao(@Valid @RequestBody AberturaSessaoDTO aberturaSessaoDTO){
		sessaoService.abrirSessaoVotacao(aberturaSessaoDTO);
		return new ResponseEntity<>(HttpStatus.OK); 
	}

}
