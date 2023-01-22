package br.com.vitt.apivotacao.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.entities.enums.Status;
import br.com.vitt.apivotacao.services.exceptions.ValidationException;


@SpringBootTest
public class AssociadoServiceTest {
	
	@Autowired
	AssociadoService service;
	
	@Test	
	public void insert() {
		Associado associado = new Associado("TestNome","20051739089");
		assertTimeout(Duration.ofSeconds(1), ()->{
			Associado entity =service.insert(associado);			
			assertNotNull(entity.getId());				
		});		
	}
	
	@Test
	public void insertCPFRepetidoException() {
		Associado associado = new Associado("TestNome2", "77361791077");		
		service.insert(associado);
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{
				service.insert(associado);			
			});
		});		
	}
	
	@Test
	public void insertCPFsInvalidosException() {
		Associado associado = new Associado("TestNome3", "");	
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{
				service.insert(associado);			
			});
		});
		associado.setCpf("11111111111");
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{
				service.insert(associado);			
			});
		});
		associado.setCpf("111111");
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{
				service.insert(associado);			
			});
		});
	}
	
	@Test
	public void insertNomeNuloException() {
		Associado associado = new Associado("","65137505048");
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{
				service.insert(associado);			
			});
		});		
	}
	
	@Test
	public void findByIdID() {				
		assertTimeout(Duration.ofSeconds(1), ()->{			
				Associado entity = service.findById(1L);
				assertNotNull(entity);
		});		
	}	
	
	@Test
	public void findByIdIDEception() {				
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(ValidationException.class, ()->{
				service.findById(100L);			
			});
		});		
	}
	
	@Test	
	public void acharTodosAssociados() {
		Associado associado = new Associado("TesteListaAssociados","23396434064");
		service.insert(associado);
		assertTimeout(Duration.ofSeconds(1), ()->{			
				List<Associado> entity = service.findAll();
				assertNotNull(entity);
		});		
	}
	
	@Test
	public void verificarSeNaoAptoParaVotar() {
		Associado associado = new Associado("TestUnableToVote","59422310016",Status.UNABLE_TO_VOTE);
		service.insert(associado);
		assertTimeout(Duration.ofSeconds(1), ()->{			
				Status result = service.verificarSeAptoParaVotar("59422310016");
				Assertions.assertThat(result).isNotEqualTo(Status.ABLE_TO_VOTE);
				Assertions.assertThat(result).isEqualTo(Status.UNABLE_TO_VOTE);
		});		
	}

	@Test
	public void deleteEAtivar() {
		Associado associado = new Associado("TestDelete","81243995068");
		associado = service.insert(associado);
		Long id = associado.getId();
		
		assertTimeout(Duration.ofSeconds(1), ()->{			
			Associado entity = service.findById(id);
			assertNotNull(entity);
		});			
		
		assertTimeout(Duration.ofSeconds(1), ()->{			
			service.delete(id);
		});
		
		assertTimeout(Duration.ofSeconds(1), ()->{
			assertThrows(EntityNotFoundException.class, ()->{
			service.findById(id);				
			});
		});
		
		assertTimeout(Duration.ofSeconds(1), ()->{			
			Associado entity = service.ativarAssociado(id);
			assertNotNull(entity);
		});
	}
	
}
