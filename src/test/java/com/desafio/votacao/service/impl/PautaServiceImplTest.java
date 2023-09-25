package com.desafio.votacao.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.desafio.votacao.dto.response.PautaDTO;
import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.enums.PautaStatusEnum;
import com.desafio.votacao.repository.PautaRepository;
import com.desafio.votacao.service.VotoService;

public class PautaServiceImplTest {

	@InjectMocks
	private PautaServiceImpl pautaService;

	@Mock
	private PautaRepository pautaRepository;

	@Mock
	private VotoService votoService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSalvarPauta() {
		PautaDTO pautaDTO = new PautaDTO();
		Pauta pauta = new Pauta(pautaDTO);
		when(pautaRepository.save(any(Pauta.class))).thenReturn(pauta);
		ResponseEntity<PautaDTO> response = pautaService.salvar(pautaDTO);
		assertEquals(200, response.getStatusCodeValue());
		verify(pautaRepository, times(1)).save(any(Pauta.class));
	}

	@Test
	public void testBuscarPautaPorId() {
		Long pautaId = 1L;
		Pauta pauta = new Pauta();
		when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(pauta));
		Optional<Pauta> result = pautaService.buscarPorId(pautaId);
		assertEquals(Optional.of(pauta), result);
		verify(pautaRepository, times(1)).findById(pautaId);
	}

	@Test
	public void testAtualizarPautaVotacao() {
		Long pautaId = 1L;
		Pauta pauta = new Pauta(/* initialize with test data */);
		when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(pauta));
		pautaService.atualizarPautaVotacao(pautaId);
		assertEquals(PautaStatusEnum.AGUARDANDO_APURACAO, pauta.getStatus());
		verify(pautaRepository, times(1)).findById(pautaId);
		verify(pautaRepository, times(1)).save(pauta);
	}

 
	@Test
	public void testSalvarVotos() {
		Long votosSim = 5L;
		Long votosNao = 3L;
		Pauta pauta = new Pauta(/* initialize with test data */);
		pautaService.salvarVotos(votosSim, votosNao, pauta);
		assertEquals(5L, pauta.getQtdVotosSim());
		assertEquals(3L, pauta.getQtdVotosNao());
		assertEquals(PautaStatusEnum.APROVADA, pauta.getStatus());
		assertThat(!pauta.isAtivo());
		verify(pautaRepository, times(1)).save(pauta);
	}

}
