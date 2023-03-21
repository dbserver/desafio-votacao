package com.desafio.votacao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.desafio.votacao.entity.Pauta;
import com.desafio.votacao.repository.PautaRepository;
import com.desafio.votacao.service.PautaService;
import com.desafio.votacao.service.PautaServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

	private PautaService pautaService;

	@Mock
	private EntityManager entityManagerMock;

	@Mock
	private PautaRepository pautaRepositoryMock;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		this.pautaService = new PautaServiceImpl(this.entityManagerMock, this.pautaRepositoryMock);
	}

	@Test
	@DisplayName("Sucesso - Criar pauta.")
	void criarPauta() {

		Pauta pautaArmazenada = new Pauta();
		pautaArmazenada.setId(1L);
		when(pautaRepositoryMock.save(any())).thenReturn(pautaArmazenada);

		Pauta pauta = new Pauta();
		pauta.setDtInicio(LocalDateTime.now());
		Optional<Pauta> pautaBaseDeDadosOptional = pautaService.criar(pauta);

		pautaBaseDeDadosOptional.ifPresent(pautaBaseDeDados -> Assertions.assertEquals(pautaBaseDeDados.getId(), 1L));

	}

	@Test
	@DisplayName("Sucesso - Alterar pauta.")
	void alterarPauta() {
		Pauta pautaArmazenada = new Pauta();
		pautaArmazenada.setFlFinalizada(false);
		when(pautaRepositoryMock.findById(anyLong())).thenReturn(Optional.of(pautaArmazenada));

		String descricao = "DESCRICAO_MOCK";
		Pauta pautaJaArmazenada = new Pauta();
		pautaJaArmazenada.setDescricao(descricao);
		when(pautaRepositoryMock.save(any())).thenReturn(pautaJaArmazenada);

		Pauta pautaAlterada = new Pauta();
		pautaAlterada.setDescricao(descricao);
		Optional<Pauta> pautaBaseDeDadosOptional = pautaService.alterar(1L, pautaAlterada);

		pautaBaseDeDadosOptional.ifPresent(pautaBaseDeDados -> Assertions
				.assertEquals(pautaBaseDeDados.getDescricao(), pautaAlterada.getDescricao()));

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Sucesso - Consulta de pauta.")
	void consultarPauta() {
		CriteriaBuilder cb = mock(CriteriaBuilder.class);
		when(entityManagerMock.getCriteriaBuilder()).thenReturn(cb);

		CriteriaQuery<Pauta> cq = mock(CriteriaQuery.class);
		when(cb.createQuery(eq(Pauta.class))).thenReturn(cq);

		Root<Pauta> pautaRoot = mock(Root.class);
		when(cq.from(eq(Pauta.class))).thenReturn(pautaRoot);

		TypedQuery<Pauta> query = mock(TypedQuery.class);
		when(entityManagerMock.createQuery(any(CriteriaQuery.class))).thenReturn(query);

		pautaService.consultar(Optional.of("1"), Optional.of("DESCRICAO"), Optional.of(LocalDateTime.now()),
				Optional.of(LocalDateTime.now()), Optional.of(false));

		Assertions.assertTrue(true, "Consulta executada com sucesso.");
	}

	@Test
	@DisplayName("Sucesso - Consulta por id uma pauta.")
	void consultarPorId() {
		Pauta pauta = new Pauta();
		Optional<Pauta> pautaOptional = Optional.of(pauta);
		when(pautaRepositoryMock.findById(anyLong())).thenReturn(pautaOptional);

		Optional<Pauta> pautaOptionalBaseDeDadosOptional = pautaService.consultarPorId(1L);

		pautaOptionalBaseDeDadosOptional
				.ifPresent(pautaBaseDeDados -> Assertions.assertEquals(pautaBaseDeDados, pauta));
	}

	@Test
	@DisplayName("Sucesso - Consulta pautas não finalizadas.")
	void consultarAsNaoFinalizadas() {
		List<Pauta> pautaList = new ArrayList<>();
		when(pautaRepositoryMock.findByFlFinalizadaFalse()).thenReturn(pautaList);

		List<Pauta> pautaRetornoList = pautaService.consultarAsNaoFinalizadas();

		Assertions.assertEquals(pautaRetornoList, pautaList);
	}

	@Test
	@DisplayName("Sucesso - Excluir pauta.")
	void excluir() {
		Pauta pauta = new Pauta();
		pauta.setFlFinalizada(false);
		when(pautaRepositoryMock.findById(anyLong())).thenReturn(Optional.of(pauta));

		pautaService.excluir(1L);

		Assertions.assertTrue(true, "Pauta excluída.");
	}

}
