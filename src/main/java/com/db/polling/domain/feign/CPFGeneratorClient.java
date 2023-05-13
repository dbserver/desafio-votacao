package com.db.polling.domain.feign;

import com.db.polling.domain.feign.dto.CPFFeignRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cpfGenerator", url = "${feign.url}")
public interface CPFGeneratorClient {

  @PostMapping(value = "/ferramentas_online.php", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  String getCPF(@RequestBody CPFFeignRequest cpfRequest);
}