package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.stubs.AssembleiaStub;
import br.com.dbserver.votacao.stubs.PautaStub;
import br.com.dbserver.votacao.v1.dto.request.PautaRequest;
import br.com.dbserver.votacao.v1.dto.response.PautaResponse;
import br.com.dbserver.votacao.v1.dto.response.PautaResultadoResponse;
import br.com.dbserver.votacao.v1.entity.Assembleia;
import br.com.dbserver.votacao.v1.entity.Pauta;
import br.com.dbserver.votacao.v1.entity.Voto;
import br.com.dbserver.votacao.v1.enums.VotoEnum;
import br.com.dbserver.votacao.v1.exception.NotFoundException;
import br.com.dbserver.votacao.v1.exception.ValidationException;
import br.com.dbserver.votacao.v1.mapper.Resposta;
import br.com.dbserver.votacao.v1.repository.PautaRepository;
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
@DisplayName("Pauta Service Impl")
class PautaServiceImplTest {

	@InjectMocks
	private PautaServiceImpl pautaService;

	@Mock
	private PautaRepository pautaRepository;

	@Mock
	private AssembleiaServiceImpl assembleiaService;

	private Assembleia assembleia;
	private Pauta pautaMock;
	private PautaRequest pautaRequest;
	private PautaResponse pautaResponse;

	@BeforeEach
	void inicializar() {
		openMocks(this);
		assembleia = AssembleiaStub.construirAssembleiaComId();
		pautaMock = PautaStub.construirPauta();
		pautaRequest = PautaStub.construirPautaRequest();
		pautaResponse = PautaStub.construirPautaResponse();
	}

	@Test
	@DisplayName("Deve criar uma pauta corretamente")
	void criarPauta() {
		when(assembleiaService.buscarPorID(1L)).thenReturn(assembleia);
		when(pautaRepository.save(any(Pauta.class))).thenReturn(pautaMock);

		PautaResponse pautaResponse = pautaService.criarPauta(pautaRequest);

		assertEquals(pautaMock.getStatus(), pautaResponse.getStatus());
		assertEquals(1L, pautaResponse.getId());
		assertEquals(pautaMock.getDescricao(), pautaResponse.getDescricao());
		assertEquals(pautaMock.getInicio(), pautaResponse.getInicio());
		assertEquals(pautaMock.getFim(), pautaResponse.getFim());
		verify(assembleiaService, times(1)).buscarPorID(any(Long.class));
		verify(pautaRepository, times(1)).save(any(Pauta.class));
	}

	@Test
	@DisplayName("Nao deve criar uma pauta com datas divergentes")
	void naoDeveCriarPautaComDatasDivergentes() {

		pautaRequest.setInicio(LocalDateTime.now().plusYears(1));

		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () ->
				pautaService.criarPauta(pautaRequest));

		assertEquals(validationException.getMessage(), "Datas inválidas!");
	}

	@Test
	@DisplayName("Nao deve criar uma pauta com data de inicio menor que data atual")
	void naoDeveCriarPautaComDataDeInicioMenorQueHoje() {

		pautaRequest.setInicio(LocalDateTime.now().minusDays(1));
		pautaRequest.setFim(LocalDateTime.now());

		ValidationException validationException =
				Assertions.assertThrows(ValidationException.class, () ->
						pautaService.criarPauta(pautaRequest));

		assertEquals(validationException.getMessage(), "Datas inválidas!");
	}

	@Test
	@DisplayName("Busca uma pauta pelo ID e apresenta o resultado atual da Pauta")
	void buscarPorId() {
		Voto votoNao = Voto.builder().valor(VotoEnum.NAO).build();
		Voto votoSim = Voto.builder().valor(VotoEnum.SIM).build();
		pautaMock.getVotos().addAll(List.of(votoSim, votoSim, votoSim, votoNao, votoNao, votoNao));

		when(pautaRepository.findById(1L)).thenReturn(Optional.of(pautaMock));

		PautaResultadoResponse response = pautaService.buscarPorID(1L);

		assertEquals(pautaMock.getId(), response.getId());
		assertEquals(3, response.getVotoNao());
		assertEquals(3, response.getVotoSim());

		verify(pautaRepository, times(1)).findById(any(Long.class));
	}

	@Test
	@DisplayName("Busca as pautas com paginacao")
	void buscarTodas() {

		Pageable pageable = Pageable.unpaged();

		Page<Pauta> pageMock = new PageImpl<>(List.of(pautaMock));
		when(pautaRepository.findAll(pageable)).thenReturn(pageMock);

		Resposta<PautaResponse> response = pautaService.buscarTodas(pageable);

		assertNotNull(response);
		assertEquals(response.getTotalPaginas(), 1, "Deve ter apenas 1 página");
		assertEquals(response.getLista().size(), 1, "Deve ter apenas 1 item na lista");
		assertEquals(response.getLista().get(0), pautaResponse, "Deve ter a pauta esperada");
		verify(pautaRepository, times(1)).findAll(any(Pageable.class));
	}

	@Test
	@DisplayName("Deve dar Not Found Exception ao buscar id inexistente ")
	void deveDarExceptionAoBuscarIdInexistente() {
		when(pautaRepository.findById(1L)).thenReturn(Optional.empty());

		NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () ->
				pautaService.buscarPorId(1L));

		assertEquals(notFoundException.getMessage(), "ID não encontrado!");
		verify(pautaRepository, times(1)).findById(any(Long.class));
	}
}