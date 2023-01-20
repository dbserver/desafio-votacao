package br.com.vitt.apivotacao.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.vitt.apivotacao.entities.Associado;
import br.com.vitt.apivotacao.tests.Factory;

@DataJpaTest
public class AssociadoRepositoryTest {

	@Autowired
	private AssociadoRepository repository;
	private long idInvalido;
	private long contadorAssociados;
	
	@BeforeEach
	void setUp() throws Exception{
		idInvalido = 1000L;
		contadorAssociados = 0L;
	}
	
	@Test
	public void salvarDevePersistirComIncrementoAutomaticoQuandoOIdENulo() {
		Associado associado = Factory.createAssociado();
		associado.setId(null);
		associado = repository.save(associado);
		
		Assertions.assertNotNull(associado.getId());
		Assertions.assertEquals(contadorAssociados + 1, associado.getId());
	}
	
	@Test
	public void deleteDeveExcluirOObjetoQuandoOIdExistir() {
		Associado associado = Factory.createAssociadoDois();
		associado.setId(null);
		associado = repository.save(associado);
		repository.deleteById(2L);
		Optional<Associado> result = repository.findById(2L);
		
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteDeveLancarResultDataAccessExceptionVazioQuandoOIdNaoExiste() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(idInvalido);
		});
	}
	
	@Test
	public void findByIdDeveRetornarOptionalAssociadoQuandoOIdExiste() {
		Associado associado = Factory.createAssociadoTres();
		associado.setId(null);
		associado = repository.save(associado);
		Optional<Associado> result = repository.findById(3L);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdDeveRetornarOptionalAssociadoNullQuandoIdNaoExiste() {
		Optional<Associado> result = repository.findById(idInvalido);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
