package com.cooperativa.votacao.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cooperativa.votacao.MassaDados;
import com.cooperativa.votacao.dto.PautaDTO;
import com.cooperativa.votacao.entity.ContabilizacaoVotacaoPauta;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.exception.PautaNaoEncontradaException;
import com.cooperativa.votacao.mapper.PautaMapper;
import com.cooperativa.votacao.repository.ContablizacaoVotacaoRepository;
import com.cooperativa.votacao.repository.PautaRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PautaServiceTest {
	
	@InjectMocks
	private PautaServiceImpl pautaService;
	
	@Mock
	private PautaRepository pautaRepository;
	
	@Mock
	private ContablizacaoVotacaoRepository contablizacaoVotacaoRepository;
	
	@Mock
	private PautaMapper pautaMapper;
	
	
	
	@Test
	public void cadastrar() {
		Mockito.when(this.pautaRepository.save(any(PautaEntity.class))).thenReturn(MassaDados.getPautaEntity(StatusSessaoEnum.CRIADO));
		Mockito.when(this.pautaMapper.entityToDTO(any(PautaEntity.class))).thenReturn(MassaDados.getPautaDTO());
		
		PautaDTO pauta = pautaService.cadastrar(MassaDados.getPautaDTO());
		
		assertNotNull(pauta);
		
	}
	
	@Test
	public void buscarPorId() {
		Mockito.when(this.pautaRepository.findById(1)).thenReturn(Optional.of(MassaDados.getPautaEntity(StatusSessaoEnum.CRIADO)));
		
		PautaEntity pauta = pautaService.buscarPorId(1);
		
		assertNotNull(pauta);
		
	}
	
	@Test
	public void buscarPorIdInexistente() {
		Mockito.when(this.pautaRepository.findById(1)).thenReturn(Optional.empty());
		
		PautaNaoEncontradaException pautaNaoEncontradaException = Assertions.
				assertThrows(PautaNaoEncontradaException.class,()-> pautaService.buscarPorId(1));
		
		Assertions.assertEquals("Pauta não encontrada", pautaNaoEncontradaException.getMessage());
		
	} 
	
	
	@Test
	public void buscarContabilizacaoVotacaoPorPauta() {
		ContabilizacaoVotacaoPauta contabilizacaoVotacaoPauta = MassaDados.getContabilizacaoVotacaoPauta();
		Mockito.when(this.pautaRepository.findById(1)).thenReturn(Optional.of(MassaDados.getPautaEntity(StatusSessaoEnum.CRIADO)));
		Mockito.when(this.contablizacaoVotacaoRepository.buscarContablizacaoVotacaoPorPauta(1)).thenReturn(contabilizacaoVotacaoPauta);
		
		int total= contabilizacaoVotacaoPauta.getTotalNao()+contabilizacaoVotacaoPauta.getTotalSim();
		
		ContabilizacaoVotacaoPauta contabilizacaoVotacaoPautaRetorno =pautaService.buscarContabilizacaoVotacaoPorPauta(1);
		
		assertTrue(total==contabilizacaoVotacaoPautaRetorno.getTotalNao()+contabilizacaoVotacaoPautaRetorno.getTotalSim());
	}
	
	@Test
    public void	 buscarPorStatusSessao() {
		StatusSessaoEntity sessaoEntity = new StatusSessaoEntity(StatusSessaoEnum.ABERTO);
		Mockito.when(this.pautaRepository.findByStatusSessao(sessaoEntity)).thenReturn(MassaDados.getListPautaEntity());
		
		List<PautaEntity> listPautaEntity=pautaService.buscarPorStatusSessao(sessaoEntity);
		
		assertTrue(!listPautaEntity.isEmpty());
		
	}
	
	
	
	

}
