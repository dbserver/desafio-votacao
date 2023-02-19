package br.com.dbserver.votacao.v1.service;

import br.com.dbserver.votacao.v1.client.CpfClient;
import br.com.dbserver.votacao.v1.client.CpfResponse;
import br.com.dbserver.votacao.v1.client.CpfClientImpl;
import br.com.dbserver.votacao.v1.dto.request.AssociadoRequest;
import br.com.dbserver.votacao.v1.dto.response.AssociadoResponse;
import br.com.dbserver.votacao.v1.entity.Associado;
import br.com.dbserver.votacao.v1.enums.StatusUsuarioEnum;
import br.com.dbserver.votacao.v1.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.dbserver.votacao.stubs.AssociadoStub.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AssociadoServiceImplTest {

	@InjectMocks
	private AssociadoServiceImpl associadoService;

	@Mock
	private AssociadoRepository associadoRepositoryMock;

	@Mock
	CpfClientImpl cpfclientImpl;

	@Spy
	CpfClient cpfClient;


	private  AssociadoResponse associadoResponse =
			construirAssociadoResponse(StatusUsuarioEnum.PODE_VOTAR);
	private  AssociadoRequest associadoRequest =
			construirAssociadoRequest(StatusUsuarioEnum.PODE_VOTAR);
	private  Associado associado = construirAssociado();
	private  CpfResponse cpfResponse = new CpfResponse();

	@Test
	void salvar() {
		cpfResponse.setSituacao("Regular");
		when(associadoRepositoryMock.save(any(Associado.class))).thenReturn(associado);
		when(cpfClient.buscarCpf(any(String.class))).thenReturn(cpfResponse);
		assertEquals(associadoResponse, associadoService.salvar(associadoRequest));
	}

	@Test
	void buscarPorCpfOuCnpj() {
		when(associadoRepositoryMock.findByDocumento(any())).thenReturn(Optional.of(associado));
		assertEquals(associadoResponse, associadoService.buscarPorCpfOuCnpj("90015955028"));
	}
}