package com.desafio.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.votacao.dto.response.PautaDTO;
import com.desafio.votacao.service.PautaService;

@RestController
@RequestMapping("/pauta")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PautaController {
	
	private PautaService pautaService;

	@Autowired
	public PautaController(PautaService pautaService) {
		this.pautaService = pautaService;
	}

	@PostMapping("/save")
	public ResponseEntity<PautaDTO> save(@RequestBody PautaDTO dto) {
		return this.pautaService.salvar(dto);
	}
	
	@PostMapping("/buscar/{id}")
	public ResponseEntity<PautaDTO> buscarPorId(@PathVariable("id") Long pautaID) {
		return ResponseEntity.ok(new PautaDTO(this.pautaService.buscarPorId(pautaID).get()));
	}
}
