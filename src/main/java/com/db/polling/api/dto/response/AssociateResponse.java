package com.db.polling.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateResponse {

  private Long associateId;
  private String name;
  private String cpf;

}
