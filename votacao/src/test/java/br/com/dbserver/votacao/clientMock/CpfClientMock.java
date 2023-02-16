package br.com.dbserver.votacao.clientMock;

import br.com.dbserver.votacao.v1.client.CpfClient;
import br.com.dbserver.votacao.v1.client.CpfClientImpl;
import br.com.dbserver.votacao.v1.client.CpfResponse;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CpfClientMock {

	@MockBean
	public static CpfClient cpfClient;

	public void mockCpfClientValidarCpf(){
		when(cpfClient.buscarCpf(any(String.class))).thenReturn(new CpfResponse("Regular"));
	}
}