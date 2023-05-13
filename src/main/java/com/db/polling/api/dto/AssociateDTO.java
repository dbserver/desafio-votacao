package com.db.polling.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateDTO {

  @NotBlank(message = "The associate name cannot be blank.")
  private String name;

  @NotNull(message = "The associate CPF cannot be null.")
  @CPF(message = "This cpf is not valid")
  private String cpf;

}
