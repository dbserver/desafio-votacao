package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.stubs.AssociadoStub;
import br.com.dbserver.votacao.stubs.PautaStub;
import br.com.dbserver.votacao.stubs.VotoStub;
import br.com.dbserver.votacao.v1.dto.request.VotoRequest;
import br.com.dbserver.votacao.v1.dto.response.VotoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.entity.Pauta;
import br.com.dbserver.votacao.v1.entity.Voto;
import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import br.com.dbserver.votacao.v1.exception.ValidationException;
import br.com.dbserver.votacao.v1.repository.VotoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@DisplayName("Voto Service Impl")
class VotoServiceImplTest {

	@InjectMocks
	VotoServiceImpl votoService;

	@Mock
	private VotoRepository votoRepository;

	@Mock
	private AssociadoServiceImpl associadoService;

	@Mock
	private PautaServiceImpl pautaService;

	private Associado associado;
	private VotoRequest votoRequest;
	private Pauta pauta;
	private Voto voto;
	private final String CPF = "90015955028";

	@BeforeEach
	void inicializar() {
		openMocks(this);
		associado = AssociadoStub.construirAssociado();
		votoRequest = VotoStub.criarVotoRequest();
		pauta = PautaStub.construirPauta();
		voto = VotoStub.criarVoto(pauta, associado);
	}

	@Test
	@DisplayName("Deve salvar um voto corretamente")
	void salvar() {

		when(associadoService.buscarPorDocumento(CPF)).thenReturn(associado);
		when(pautaService.buscarPorId(1L)).thenReturn(pauta);
		when(votoRepository.save(any(Voto.class))).thenReturn(voto);
		when(votoRepository.existsByAssociadoIdAndPautaId(associado.getId(), pauta.getId())).thenReturn(false);

		VotoResponse votoResponse = votoService.salvar(votoRequest);

		assertEquals(votoRequest.getDocumentoAssociado(), votoResponse.getAssociado().getDocumento());
		verify(associadoService, times(1)).buscarPorDocumento(any(String.class));
		verify(pautaService, times(1)).buscarPorId(1L);
		verify(votoRepository, times(1)).save(any(Voto.class));
		verify(votoRepository, times(1))
				.existsByAssociadoIdAndPautaId(associado.getId(), pauta.getId());
	}

	@Test
	@DisplayName("Deve dar erro quando Associado nao tem autorizacao para votar")
	void deveDarErroQuandoAssociadoNaoPodeVotar() {
		associado.setStatus(StatusUsuarioEnum.NAO_PODE_VOTAR);
		when(associadoService.buscarPorDocumento(CPF)).thenReturn(associado);

		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () ->
				votoService.salvar(votoRequest));

		assertEquals("Associado : " + CPF + " - não pode votar!", validationException.getMessage());
		verify(associadoService, times(1)).buscarPorDocumento(any(String.class));
		verify(votoRepository, times(0)).save(any(Voto.class));
		verify(pautaService, times(0)).buscarPorId(1L);
		verify(votoRepository, times(0))
				.existsByAssociadoIdAndPautaId(associado.getId(), pauta.getId());
	}

	@Test
	@DisplayName("Deve dar erro quando Pauta já expirou")
	void deveDarErroQuandoPautaExpirada() {
		pauta.setFim(LocalDateTime.now().minusYears(1));

		when(associadoService.buscarPorDocumento(CPF)).thenReturn(associado);
		when(pautaService.buscarPorId(1L)).thenReturn(pauta);

		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () ->
				votoService.salvar(votoRequest));

		assertEquals("Pauta de ID: " + pauta.getId() + " - já expirou o tempo!", validationException.getMessage());
		verify(associadoService, times(1)).buscarPorDocumento(any(String.class));
		verify(votoRepository, times(0)).save(any(Voto.class));
		verify(pautaService, times(1)).buscarPorId(1L);
		verify(votoRepository, times(0))
				.existsByAssociadoIdAndPautaId(associado.getId(), pauta.getId());
	}

	@Test
	@DisplayName("Deve dar erro se Associado ja votou na pauta")
	void erroAoDuplicarVoto() {

		when(associadoService.buscarPorDocumento(CPF)).thenReturn(associado);
		when(pautaService.buscarPorId(1L)).thenReturn(pauta);
		when(votoRepository.existsByAssociadoIdAndPautaId(associado.getId(), pauta.getId())).thenReturn(true);

		ValidationException validationException = Assertions.assertThrows(ValidationException.class, () ->
				votoService.salvar(votoRequest));

		assertEquals("Associado já votou nesta pauta!", validationException.getMessage());
		verify(associadoService, times(1)).buscarPorDocumento(any(String.class));
		verify(pautaService, times(1)).buscarPorId(1L);
		verify(votoRepository, times(0)).save(any(Voto.class));
		verify(votoRepository, times(1))
				.existsByAssociadoIdAndPautaId(associado.getId(), pauta.getId());
	}
}