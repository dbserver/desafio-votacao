package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.client.CpfClient;
import br.com.dbserver.votacao.v1.client.CpfClientImpl;
import br.com.dbserver.votacao.v1.client.CpfResponse;
import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import br.com.dbserver.votacao.v1.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static br.com.dbserver.votacao.stubs.AssociadoStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Associado Service Impl")
class AssociadoServiceImplTest {

	@InjectMocks
	private AssociadoServiceImpl associadoService;

	@Mock
	private AssociadoRepository associadoRepositoryMock;

	@Mock
	CpfClientImpl cpfclientImpl;

	@Spy
	CpfClient cpfClient;

	@BeforeEach
	void inicializar() {
		MockitoAnnotations.openMocks(this);
	}

	private final AssociadoResponse associadoResponse =
			construirAssociadoResponse(StatusUsuarioEnum.PODE_VOTAR);
	private final AssociadoRequest associadoRequest =
			construirAssociadoRequest(StatusUsuarioEnum.PODE_VOTAR);
	private final Associado associado = construirAssociado();
	private final CpfResponse cpfResponse = new CpfResponse();

	@Test
	void salvar() {
		cpfResponse.setSituacao("Regular");
		when(associadoRepositoryMock.save(any(Associado.class))).thenReturn(associado);
		when(cpfClient.buscarCpf(any(String.class))).thenReturn(cpfResponse);
		assertEquals(associadoResponse, associadoService.salvar(associadoRequest));
		verify(associadoRepositoryMock, times(1)).save(any(Associado.class));
		verify(cpfclientImpl, times(1)).validarCpf(any(String.class));
	}

	@Test
	void buscarPorCpfOuCnpj() {
		when(associadoRepositoryMock.findByDocumento(any())).thenReturn(Optional.of(associado));
		assertEquals(associadoResponse, associadoService.buscarPorCpfOuCnpj("90015955028"));
		verify(associadoRepositoryMock, times(1)).findByDocumento(any(String.class));
	}
}