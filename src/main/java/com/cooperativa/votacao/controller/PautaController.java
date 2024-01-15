package com.cooperativa.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.entity.ContabilizacaoVotacaoPauta;
import com.cooperativa.votacao.service.PautaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pauta")
public class PautaController {
	
	@Autowired
	private PautaService pautaService;

	
	@PostMapping
	public ResponseEntity<PautaDTO> cadastrar(@Valid @RequestBody PautaDTO pautaDTO){
		return ResponseEntity.status(HttpStatus.CREATED).body(pautaService.cadastrar(pautaDTO));
	}
	
	@GetMapping("/contabilizacaoVotacao/{idPauta}")
	public ResponseEntity<ContabilizacaoVotacaoPauta> buscarContabilizacaoVotacaoPorPauta(@PathVariable Integer idPauta){
		return ResponseEntity.status(HttpStatus.OK).body(pautaService.buscarContabilizacaoVotacaoPorPauta(idPauta));
	}
}
