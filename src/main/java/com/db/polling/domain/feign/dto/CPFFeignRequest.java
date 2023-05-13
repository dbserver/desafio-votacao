package com.db.polling.domain.feign.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CPFFeignRequest {

  String acao;
  String pontuacao;
  String cpf_estado;

}
