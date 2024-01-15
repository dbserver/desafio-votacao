package com.cooperativa.votacao.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cooperativa.votacao.MassaDados;
import com.cooperativa.votacao.dto.AberturaSessaoDTO;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.exception.SessaoException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SessaoServiceTest {
	
	@InjectMocks
	private SessaoServiceImpl sessaoService;
	
	@Mock
	private PautaService pautaService;
	
	
	@Test
	public void abrirSessaoVotacao() {
		Mockito.when(this.pautaService.buscarPorId(1)).thenReturn(MassaDados.getPautaEntity(StatusSessaoEnum.CRIADO));
		
		sessaoService.abrirSessaoVotacao(MassaDados.getAberturaSessaoDTO());
		
		verify(pautaService).atualizar(any(PautaEntity.class));
	}
	
	@Test
	public void abrirSessaoVotacaoTempoSessaoNulo() {
		AberturaSessaoDTO aberturaSessaoDTO = MassaDados.getAberturaSessaoDTO();
		aberturaSessaoDTO.setTempoSessao(null);
		
		Mockito.when(this.pautaService.buscarPorId(1)).thenReturn(MassaDados.getPautaEntity(StatusSessaoEnum.CRIADO));
		
		sessaoService.abrirSessaoVotacao(MassaDados.getAberturaSessaoDTO());
		
		verify(pautaService).atualizar(any(PautaEntity.class));
	}
	
	@Test
	public void testarSessaoAberta() {
		Mockito.when(this.pautaService.buscarPorId(1)).thenReturn(MassaDados.getPautaEntity(StatusSessaoEnum.ABERTO));
		
		SessaoException SessaoException = Assertions.
				assertThrows(SessaoException.class,()-> 
				sessaoService.abrirSessaoVotacao(MassaDados.getAberturaSessaoDTO()));
		
		Assertions.assertEquals("Sessão de votação já foi aberta", SessaoException.getMessage());
		
	} 
	
	@Test
	public void testarSessaoFinalizada() {
		Mockito.when(this.pautaService.buscarPorId(1)).thenReturn(MassaDados.getPautaEntity(StatusSessaoEnum.FINALIZADO));
		
		SessaoException SessaoException = Assertions.
				assertThrows(SessaoException.class,()-> 
				sessaoService.abrirSessaoVotacao(MassaDados.getAberturaSessaoDTO()));
		
		Assertions.assertEquals("Sessão de votação já foi finalizada", SessaoException.getMessage());
		
	} 

}
