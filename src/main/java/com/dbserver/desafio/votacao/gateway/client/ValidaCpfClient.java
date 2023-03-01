package com.dbserver.desafio.votacao.gateway.client;


import com.dbserver.desafio.votacao.gateway.dto.CpfStatusRespostaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${feign.valida.cpf.name}", url = "${feign.valida.cpf.url}")
public interface ValidaCpfClient {

    @GetMapping(value = "/{cpfAssociado}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CpfStatusRespostaDTO> validaClient(@PathVariable("cpfAssociado") String cpfAssociado);
}
