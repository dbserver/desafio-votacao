package com.desafio.votacao.controller;

import java.util.List;
import java.util.Optional;

import com.desafio.votacao.entity.Votacao;
import com.desafio.votacao.enums.VotoEnumDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.votacao.dto.VotacaoDTO;
import com.desafio.votacao.dto.VotoDTO;
import com.desafio.votacao.service.SessaoVotacaoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/v1/votacao", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SessaoVotacaoControllerImpl implements SessaoVotacaoController {
    
    private final SessaoVotacaoService votacaoService;

    @PostMapping
	public ResponseEntity<Void> votar(VotoDTO votoDTO) {
		Optional<Votacao> votacaoOptional = this.votacaoService.votar(votoDTO);
    	if (votacaoOptional.isPresent()) {
    		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().queryParam("idPauta", votoDTO.getIdPauta()).queryParam("idAssociado", votoDTO.getIdAssociado()).queryParam("voto", votoDTO.getVoto()).build().toUri()).build();
		} else {
			return ResponseEntity.unprocessableEntity().build();
		}
	}

    @GetMapping
	public ResponseEntity<List<VotacaoDTO>> consultar(Long idPauta, Optional<Long> idAssociado,
			Optional<String> voto) {
		Optional<List<Votacao>> pautaOptionalList = this.votacaoService.consultar(idPauta, idAssociado, voto);
    		return ResponseEntity.noContent().build();
	}
}
