package com.dbserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cpf-validator",url = "${url.cpf-validator}", dismiss404 = true)
public interface CpfValidatorClient {
    @GetMapping("/user/validate/{cpf}")
    ResponseEntity<ValidationDTO> validate(@PathVariable String cpf);
}
