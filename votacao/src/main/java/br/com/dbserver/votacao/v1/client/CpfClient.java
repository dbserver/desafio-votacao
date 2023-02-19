package br.com.dbserver.votacao.v1.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpfcnpj", url = "${api.validacao.cpf.url}")
public interface CpfClient {

	@GetMapping(value = "/5ae973d7a997af13f0aaf2bf60e65803/9/{cpf}")
	CpfResponse buscarCpf(@PathVariable("cpf") String cpf);
}
