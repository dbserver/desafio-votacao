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
import com.cooperativa.votacao.service.VotoService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VotoControllerTest {
	
	@InjectMocks
	private VotoController votoController;
	
	@Mock
	private VotoService votoService;
	
	@Test
	public void votar() {
		ResponseEntity<Void> retorno = this.votoController.votar(MassaDados.getVotoDTO());
		assertTrue(retorno.getStatusCode()== HttpStatus.OK );
		assertNotNull(retorno);	
		
	}

}
