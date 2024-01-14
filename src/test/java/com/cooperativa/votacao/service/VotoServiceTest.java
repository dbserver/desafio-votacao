package com.cooperativa.votacao.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cooperativa.votacao.MassaDados;
import com.cooperativa.votacao.dto.VotoDTO;
import com.cooperativa.votacao.entity.PautaEntity;
import com.cooperativa.votacao.entity.StatusSessaoEnum;
import com.cooperativa.votacao.entity.VotoEntity;
import com.cooperativa.votacao.exception.SessaoException;
import com.cooperativa.votacao.exception.VotoInvalidoException;
import com.cooperativa.votacao.mapper.VotoMapper;
import com.cooperativa.votacao.repository.VotoRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VotoServiceTest {
	
	@InjectMocks
	private VotoServiceImpl votoService;
	
	@Mock
	private PautaService pautaService;
	
	@Mock
	private SessaoService sessaoService;
	
	@Mock
	private VotoRepository votoRepository;
	
	@Mock
	private VotoMapper votoMapper;
	
	@Test
	public void votar() {
		PautaEntity pautaEntity = MassaDados.getPautaEntity(StatusSessaoEnum.ABERTO);
		pautaEntity.setTempoSessao(LocalDateTime.now().plusDays(1));
		
		Mockito.when(this.pautaService.buscarPorId(1)).thenReturn(pautaEntity);
		Mockito.when(this.votoMapper.DTOtoEntity(any(VotoDTO.class))).thenReturn(MassaDados.getVotoEntity());
		
		votoService.votar(MassaDados.getVotoDTO());
		
		verify(votoRepository).save(any(VotoEntity.class));
			
	}
	
	
	@Test
	public void votarTempoSessaoExpirado() {
		PautaEntity pautaEntity = MassaDados.getPautaEntity(StatusSessaoEnum.ABERTO);
		pautaEntity.setTempoSessao(LocalDateTime.now().minusHours(5));
		
		Mockito.when(this.pautaService.buscarPorId(1)).thenReturn(pautaEntity);
		
		SessaoException SessaoException = Assertions.
				assertThrows(SessaoException.class,()-> 
				votoService.votar(MassaDados.getVotoDTO()));
		
		Assertions.assertEquals("Sessão de votação já foi finalizada", SessaoException.getMessage());
			
	}
	
	@Test
	public void votarNovamente() {
		PautaEntity pautaEntity = MassaDados.getPautaEntity(StatusSessaoEnum.ABERTO);
		pautaEntity.setTempoSessao(LocalDateTime.now().plusDays(1));
		
		Mockito.when(this.pautaService.buscarPorId(1)).thenReturn(pautaEntity);
        Mockito.when(this.votoRepository.countByPautaAndIdAssociado(any(PautaEntity.class), anyInt())).thenReturn(1);
		
		
        VotoInvalidoException votoInvalidoException = Assertions.
				assertThrows(VotoInvalidoException.class,()-> 
				votoService.votar(MassaDados.getVotoDTO()));
		
		Assertions.assertEquals("Você já votou", votoInvalidoException.getMessage());
			
	}

	@Test
	public void votarVotoInvalido() {
		PautaEntity pautaEntity = MassaDados.getPautaEntity(StatusSessaoEnum.ABERTO);
		pautaEntity.setTempoSessao(LocalDateTime.now().plusDays(1));
		
		VotoDTO votoDTO = MassaDados.getVotoDTO();
		votoDTO.setVoto("nem");
		
		Mockito.when(this.pautaService.buscarPorId(1)).thenReturn(pautaEntity);
       
		VotoInvalidoException votoInvalidoException = Assertions.
				assertThrows(VotoInvalidoException.class,()-> 
				votoService.votar(votoDTO));
		
		Assertions.assertEquals("Voto apenas pode ser 'Sim'/'Não'", votoInvalidoException.getMessage());
			
	}

}
