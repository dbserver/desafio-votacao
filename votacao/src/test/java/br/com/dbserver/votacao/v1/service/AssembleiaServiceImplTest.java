package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.stubs.AssembleiaStub;
import br.com.dbserver.votacao.v1.dto.request.AssembleiaRequest;
import br.com.dbserver.votacao.v1.dto.response.AssembleiaResponse;
import br.com.dbserver.votacao.v1.entity.Assembleia;
import br.com.dbserver.votacao.v1.exception.NotFoundException;
import br.com.dbserver.votacao.v1.exception.ValidationException;
import br.com.dbserver.votacao.v1.mapper.Resposta;
import br.com.dbserver.votacao.v1.repository.AssembleiaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("Assembleia Service Impl")
class AssembleiaServiceImplTest {

	@InjectMocks
	private AssembleiaServiceImpl assembleiaService;

	@Mock
	private AssembleiaRepository assembleiaRepository;

	AssembleiaRequest assembleiaRequest;
	Assembleia assembleiaMock;
	AssembleiaResponse assembleiaResponseEsperada;


	@BeforeEach
	void inicializar() {
		openMocks(this);
		assembleiaRequest = AssembleiaStub.construirAssembleiaRequest();
		assembleiaMock = AssembleiaStub.construirAssembleiaComId();
		assembleiaResponseEsperada = AssembleiaStub.construirAssembleiaResponse();
	}


	@Test
	@DisplayName("Deve criar uma assembleia com sucesso")
	void criar() {
		when(assembleiaRepository.save(any(Assembleia.class))).thenReturn(assembleiaMock);
		AssembleiaResponse response = assembleiaService.criar(assembleiaRequest);

		assertEquals(assembleiaResponseEsperada, response);
		verify(assembleiaRepository, times(1)).save(any(Assembleia.class));
	}

	@Test
	@DisplayName("Deve buscar todos as assembleias com sucesso")
	void buscarTodas() {
		Pageable pageable = Pageable.unpaged();
		assembleiaMock.setId(1L);
		Page<Assembleia> pageMock = new PageImpl<>(List.of(assembleiaMock));
		when(assembleiaRepository.findAll(pageable)).thenReturn(pageMock);


		Resposta<AssembleiaResponse> response = assembleiaService.buscarTodas(pageable);

		assertNotNull(response);
		assertEquals(response.getTotalPaginas(), 1, "Deve ter apenas 1 página");
		assertEquals(response.getLista().size(), 1, "Deve ter apenas 1 item na lista");
		assertEquals(response.getLista().get(0), assembleiaResponseEsperada, "Deve ter a assembleia esperada");
		verify(assembleiaRepository, times(1)).findAll(any(Pageable.class));
	}

	@Test
	@DisplayName("Deve buscar uma assembleia por ID")
	void buscarPorId() {
		Long id = 1L;
		when(assembleiaRepository.findById(id)).thenReturn(Optional.of(assembleiaMock));
		Assembleia response = assembleiaService.buscarPorID(id);

		assertEquals(assembleiaMock, response);
	}

	@Test
	@DisplayName("Deve disparar NotFoundException ao buscar uma assembleia com inexistente")
	void buscarPorIDInexistente() {
		Long id = 1L;

		when(assembleiaRepository.findById(id)).thenReturn(Optional.empty());

		NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () ->
				assembleiaService.buscarPorID(id));

		assertEquals(notFoundException.getMessage(), "Assembleia não encontrada!");
	}

	@Test
	@DisplayName("Deve disparar ValidationException ao criar uma assembleia com data invalida")
	void criarComDataInvalida() {
		assembleiaRequest.setFim(LocalDateTime.now().minusYears(1));

		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () ->
				assembleiaService.criar(assembleiaRequest));

		assertEquals(validationException.getMessage(), "Data inicio não pode ser superior a data fim e inferior a data atual");
	}
}