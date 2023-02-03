package com.br.dbserver.votacao.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.dbserver.votacao.dto.DadosResultado;
import com.br.dbserver.votacao.dto.DadosVoto;
import com.br.dbserver.votacao.model.Associado;
import com.br.dbserver.votacao.model.Pauta;
import com.br.dbserver.votacao.model.TipoVoto;
import com.br.dbserver.votacao.model.Voto;
import com.br.dbserver.votacao.repository.VotoRepository;

import jakarta.validation.Valid;

@Service
public class VotacaoService {
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private AssociadoService associadoService;
	
	public DadosResultado resultadoVotacao(Long idPauta) {
		Pauta pauta = pautaService.findPautaById(idPauta);
		
		if(LocalDateTime.now().isAfter(pauta.getFimVotacao())) {
			
			salvarResultado(pauta);
			DadosResultado dados = new DadosResultado(
					pauta.getId(), 
					pauta.getTema(), 
					pauta.getInicioVotacao(),
					pauta.getFimVotacao(),
					pauta.getResultadoVotacao());
			return dados;
		}
		return null;
		//mensagem de sessão não finalizada
	}
	
	private void salvarResultado(Pauta pauta) {
		
 		Map<TipoVoto, Long> resultado = new HashMap<TipoVoto, Long>();
		List<Voto> listaDeVotos =  votoRepository.findByPautaId(pauta.getId());
		resultado.put(TipoVoto.SIM, listaDeVotos.stream().filter(e -> e.getTipoVoto().equals(TipoVoto.SIM)).count());
		resultado.put(TipoVoto.NAO, listaDeVotos.stream().filter(e -> e.getTipoVoto().equals(TipoVoto.NAO)).count());
		
		pautaService.atualizarResultado(pauta.getId(), resultado.toString());
	}

	public Voto salvarVoto(@Valid DadosVoto dados) {
		//verificar se usuario é apto	
		Associado associado = associadoService.findAssociadoById(dados.associadoId());
		Pauta pauta = pautaService.findPautaById(dados.pautaId());
		Voto voto = new Voto(dados, pauta, associado);

		return votoRepository.save(voto);				
	}

}
