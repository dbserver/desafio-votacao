package com.desafio.votacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.votacao.dto.AssociadoBasicoDTO;
import com.desafio.votacao.dto.AssociadoCompletoDTO;
import com.desafio.votacao.entity.Associado;
import com.desafio.votacao.service.VotoAssociadoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/v1/associado", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VotoAssociadoControllerImpl implements VotoAssociadoController {
    
    private final VotoAssociadoService associadoService;

    @PostMapping
    public ResponseEntity<Void> criar(AssociadoBasicoDTO associadoBasicoDTO) {
    	Optional<Associado> associadoOptional = this.associadoService.criar(associadoBasicoDTO);
    	if (associadoOptional.isPresent()) {
    		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", associadoOptional.get().getId().toString()).build().toUri()).build();
		} else {
			return ResponseEntity.unprocessableEntity().build();
		}
    }

    @PutMapping(path = "/{id}")
	public ResponseEntity<Void> alterar(Long id, AssociadoBasicoDTO associadoBasicoDTO) {
    	Optional<Associado> associadoOptional = this.associadoService.alterar(id, associadoBasicoDTO);
    	if (associadoOptional.isPresent()) {
    		return ResponseEntity.ok().build();	
    	} else {
    		return ResponseEntity.unprocessableEntity().build();
    	}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> excluir(Long id) {
		associadoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
