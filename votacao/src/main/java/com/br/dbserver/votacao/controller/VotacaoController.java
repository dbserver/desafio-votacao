package com.br.dbserver.votacao.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.dbserver.votacao.controller.dto.DadosAbrirSessao;
import com.br.dbserver.votacao.dto.DadosCadastroPauta;
import com.br.dbserver.votacao.dto.DadosCadastroPautaComID;
import com.br.dbserver.votacao.dto.DadosVoto;
import com.br.dbserver.votacao.model.Pauta;
import com.br.dbserver.votacao.model.Voto;
import com.br.dbserver.votacao.repository.PautaRepository;
import com.br.dbserver.votacao.repository.VotoRepository;
import com.br.dbserver.votacao.service.PautaService;
import com.br.dbserver.votacao.service.VotacaoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/votacao")
public class VotacaoController {

	@Autowired
	private VotacaoService votacaoService;

	@Autowired
	private PautaService pautaService;
	
	@PostMapping("abrirsessao")
	@Transactional
	public ResponseEntity abirSessao(@RequestBody DadosAbrirSessao dados) {
		pautaService.abrirSessao(dados);
		ResponseEntity.ok();
		return null;
	}

	@PostMapping("cadastarpauta")
	@Transactional
	public ResponseEntity cadastrarPauta(@RequestBody @Valid DadosCadastroPauta dados, UriComponentsBuilder uriBuilder) { 
		Pauta pauta = pautaService.salvarPauta(dados);

		DadosCadastroPautaComID dadosComId = new DadosCadastroPautaComID(dados.tema(), pauta.getId());
		URI uri = uriBuilder.path("/votacao/cadastrarpauta/{id}").buildAndExpand(pauta.getId()).toUri();
		return ResponseEntity.created(uri).body(dadosComId);
	}

	@PostMapping("votar") 
	@Transactional
	public ResponseEntity recebervoto(@RequestBody @Valid DadosVoto dados, UriComponentsBuilder uriBuilder) { 
		Voto voto = votacaoService.salvarVoto(dados);
		URI uri = uriBuilder.path("/votacao/votar/{id}").buildAndExpand(voto.getId()).toUri();
		return ResponseEntity.created(uri).body(dados);
	}


	@GetMapping("resultadovotacao/{idPauta}")
	public ResponseEntity resultadoVotacao(@PathVariable Long idPauta) { 
		return ResponseEntity.ok(votacaoService.resultadoVotacao(idPauta));
	}
	
}
