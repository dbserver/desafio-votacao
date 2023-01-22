package br.com.vitt.apivotacao.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.vitt.apivotacao.dto.PautaDTO;
import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.entities.Pauta;
import br.com.vitt.apivotacao.services.exceptions.ValidationException;

@SpringBootTest
public class PautaServiceTest {
	
	@Autowired
	PautaService service;
	@Autowired
	AssociadoService associadoService;
	
	@Test	
	public void insert() {
		Pauta  pauta = new Pauta("TestNome");
		assertTimeout(Duration.ofSeconds(1), ()->{
			PautaDTO entity = service.insert(pauta);			
			assertNotNull(entity.getId());				
		});		
	}
		
	@Test
	public void acharPautaID() {				
		assertTimeout(Duration.ofSeconds(1), ()->{			
				Pauta entity = service.findById(1L);
				assertNotNull(entity);
		});		
	}	
	
	@Test
	public void acharPautaIDEception() {				
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{
				service.findById(100L);			
			});
		});		
	}
	
	@Test	
	public void acharTodasPautas() {
		Pauta pauta = new Pauta("TesteListaPautas");
		service.insert(pauta);
		assertTimeout(Duration.ofSeconds(1), ()->{			
				List<PautaDTO> entity = service.findAll();
				assertNotNull(entity);
		});		
	}
	
	@Test
	public void acharTodasPautasAbertas() {	
		Pauta pauta = new Pauta("TesteOpen");
		service.insert(pauta);
		assertTimeout(Duration.ofSeconds(1), ()->{			
				List<PautaDTO> entity = service.findAllOpen();
				assertNotNull(entity);
		});		
	}
	@Test
	public void acharTodasPautasAprovadas() {	
		Pauta pauta = new Pauta("TesteOpen");
		service.insert(pauta);
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(EntityNotFoundException.class, ()->{		
				service.findAllApproved();						
			});
		});		
	}
	
	@Test
	public void abrirVotacao1Exception() {
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{		
				service.abrirVotacao(1L,"");							
			});
		});	
	}
	
	@Test
	public void simularVotosPautaFechadaException(){
		Associado associado = new Associado("Exception","58804458011");
		associado = associadoService.insert(associado);							
		Pauta pauta = new Pauta("ExceptionVoto");
		PautaDTO dto = service.insert(pauta);
		
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{		
				service.votar(dto.getId(),"82092289004" , "sim");							
			});
		});
	}				
}
