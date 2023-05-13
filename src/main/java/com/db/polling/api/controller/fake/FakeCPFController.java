package com.db.polling.api.controller.fake;


import com.db.polling.api.dto.fake.CPFResponse;
import com.db.polling.api.dto.fake.CPFStatusEnum;
import com.db.polling.domain.feign.CPFGeneratorClient;
import com.db.polling.domain.feign.dto.CPFFeignRequest;
import com.db.polling.domain.utils.CPFValidatorFacadeFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/fake-cpf")
public class FakeCPFController {

  private final static String ACTION = "gerar_cpf";
  private final static String PUNCTUATION = "n";

  @Autowired
  private CPFGeneratorClient cpfGeneratorClient;

  @GetMapping
  public ResponseEntity<CPFResponse> fakeCPF() {

    CPFFeignRequest cpfFeignRequest = new CPFFeignRequest();
    cpfFeignRequest.setAcao(ACTION);
    cpfFeignRequest.setPontuacao(PUNCTUATION);
    String cpf = cpfGeneratorClient.getCPF(cpfFeignRequest);

    boolean isValid = CPFValidatorFacadeFake.isCPFValid(cpf);
    CPFResponse response = new CPFResponse();

    if (!isValid) {
      response.setStatus(CPFStatusEnum.UNABLE_TO_VOTE);
      return ResponseEntity.status(404).body(response);
    }

    response.setStatus(CPFStatusEnum.ABLE_TO_VOTE);
    return ResponseEntity.ok().body(response);
  }

}
