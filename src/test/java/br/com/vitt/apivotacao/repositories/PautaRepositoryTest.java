package br.com.vitt.apivotacao.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.vitt.apivotacao.entities.Pauta;
import br.com.vitt.apivotacao.tests.Factory;

@DataJpaTest
public class PautaRepositoryTest {

	@Autowired
	private PautaRepository repository;
	private long idInvalido;
	private long contadorPautas;
	
	@BeforeEach
	void setUp() throws Exception{
		idInvalido = 1000L;
		contadorPautas = 0L;
	}
	
	@Test
	public void salvarDevePersistirComIncrementoAutomaticoQuandoOIdENulo() {
		Pauta pauta = Factory.createPauta();
		pauta.setId(null);
		pauta = repository.save(pauta);
		
		Assertions.assertNotNull(pauta.getId());
		Assertions.assertEquals(contadorPautas + 1, pauta.getId());
	}
	
	@Test
	public void deleteDeveExcluirOObjetoQuandoOIdExistir() {
		Pauta pauta = Factory.createPautaDois();
		pauta.setId(null);
		pauta = repository.save(pauta);
		repository.deleteById(2L);
		Optional<Pauta> result = repository.findById(2L);
		
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void deleteDeveLancarResultDataAccessExceptionVazioQuandoOIdNaoExiste() {
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(idInvalido);
		});
	}
	
	@Test
	public void findByIdDeveRetornarOptionalPautaQuandoOIdExiste() {
		Pauta pauta = Factory.createPautaTres();
		pauta.setId(null);
		pauta = repository.save(pauta);
		Optional<Pauta> result = repository.findById(3L);
		
		Assertions.assertTrue(result.isPresent());
	}
	
	@Test
	public void findByIdDeveRetornarOptionalPautaNullQuandoIdNaoExiste() {
		Optional<Pauta> result = repository.findById(idInvalido);
		
		Assertions.assertTrue(result.isEmpty());
	}
}
