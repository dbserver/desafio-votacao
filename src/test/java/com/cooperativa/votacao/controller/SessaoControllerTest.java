package com.cooperativa.votacao.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cooperativa.votacao.MassaDados;
import com.cooperativa.votacao.service.SessaoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessaoControllerTest {
	
	@InjectMocks
	private SessaoController sessaoController;
	
	@Mock
	private SessaoService sessaoService;
	
	@Test
	public void abrirSessao() {
		ResponseEntity<Void> retorno = this.sessaoController.abrirSessao(MassaDados.getAberturaSessaoDTO());
		assertTrue(retorno.getStatusCode()== HttpStatus.OK );
		assertNotNull(retorno);	
		
	}

}
