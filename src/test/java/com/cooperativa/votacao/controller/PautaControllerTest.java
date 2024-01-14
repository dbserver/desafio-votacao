package com.cooperativa.votacao.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cooperativa.votacao.MassaDados;
import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.entity.ContabilizacaoVotacaoPauta;
import com.cooperativa.votacao.service.PautaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PautaControllerTest {
	
	@InjectMocks
	private PautaController pautaController;
	
	@Mock
	private PautaService pautaService;
	
	@Test
	public void cadastrar() {
		Mockito.when(this.pautaService.cadastrar(any(PautaDTO.class))).thenReturn(MassaDados.getPautaDTO());
		ResponseEntity<PautaDTO> retorno = this.pautaController.cadastrar(MassaDados.getPautaDTO());
		assertTrue(retorno.getStatusCode()== HttpStatus.CREATED );
		assertNotNull(retorno);	
		
	}
	
	@Test
	public void buscarContabilizacaoVotacaoPorPauta() {
		Mockito.when(this.pautaService.buscarContabilizacaoVotacaoPorPauta(1)).thenReturn(MassaDados.getContabilizacaoVotacaoPauta());
		ResponseEntity<ContabilizacaoVotacaoPauta> retorno = this.pautaController.buscarContabilizacaoVotacaoPorPauta(1);
		assertTrue(retorno.getStatusCode()== HttpStatus.OK );
		assertNotNull(retorno);	
		
	}

}
