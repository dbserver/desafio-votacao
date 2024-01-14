package com.cooperativa.votacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooperativa.votacao.dto.VotoDTO;
import com.cooperativa.votacao.service.VotoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/voto")
public class VotoController {
	
	@Autowired
	private VotoService votoService;
	
	
	@PostMapping
	public ResponseEntity<Void> votar(@Valid @RequestBody VotoDTO votoDTO){
		votoService.votar(votoDTO);
		return new ResponseEntity<>(HttpStatus.OK); 
	}

}
