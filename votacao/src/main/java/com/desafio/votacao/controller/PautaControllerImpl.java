package com.desafio.votacao.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.desafio.votacao.entity.Pauta;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.votacao.dto.PautaBasicoDTO;
import com.desafio.votacao.dto.PautaCompletoDTO;
import com.desafio.votacao.mapper.PautaMapper;
import com.desafio.votacao.service.PautaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/desafio-votacao/pauta", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PautaControllerImpl implements PautaController {
    
    private final PautaService pautaService;

    private final PautaMapper pautaMapper;

    @PostMapping
    public ResponseEntity<Void> criar(PautaBasicoDTO pautaDto) {
    	Optional<Pauta> pautaOptional = this.pautaService.criar(this.pautaMapper.toPauta(pautaDto));
    	if (pautaOptional.isPresent()) {
    		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().queryParam("id", pautaOptional.get().getId().toString()).build().toUri()).build();
		} else {
			return ResponseEntity.unprocessableEntity().build();
		}
    }

    @PutMapping(path = "/{id}")
	public ResponseEntity<Void> alterar(Long id, PautaBasicoDTO pautaDto) {
    	Optional<Pauta> pautaOptional = this.pautaService.alterar(id, this.pautaMapper.toPauta(pautaDto));
    	if (pautaOptional.isPresent()) {
    		return ResponseEntity.ok().build();	
    	} else {
    		return ResponseEntity.unprocessableEntity().build();
    	}
	}

	@GetMapping
	public ResponseEntity<List<PautaCompletoDTO>> consultar(Optional<String> id, Optional<String> descricao,
			Optional<LocalDateTime> dtInicio, Optional<LocalDateTime> dtFim, Optional<Boolean> excluida) {
		Optional<List<Pauta>> pautaOptionalList = this.pautaService.consultar(id, descricao, dtInicio, dtFim, excluida);
    	if (pautaOptionalList.isPresent()) {
    		return ResponseEntity.ok(this.pautaMapper.toPautaCompletoDtoList(pautaOptionalList.get()));		
    	} else {
    		return ResponseEntity.noContent().build();
    	}
	}

}
