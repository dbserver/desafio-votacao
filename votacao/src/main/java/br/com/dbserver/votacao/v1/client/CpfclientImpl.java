package br.com.dbserver.votacao.v1.client;

import br.com.dbserver.votacao.v1.exception.BadRequestException;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class CpfclientImpl {

	private final CpfClient cpfClient;

	public void validarCpf(String cpf) {
		log.info("Metodo: validarCpf - CPF: " + cpf);
		try{
			CpfResponse response = cpfClient.buscarCpf(cpf);
			if(!response.getSituacao().equals("Regular")){
				throw new BadRequestException("CPF inválido");
			}
		}catch (FeignException feignException){
			throw new BadRequestException("CPF invalido!");
		}
	}
}
