package com.db.polling.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {

  @NotBlank(message = "The agenda title cannot be blank.")
  @Size(max = 255, message = "The agenda title cannot exceed {max} characters.")
  private String title;

  @Size(max = 1000, message = "The agenda description cannot exceed {max} characters.")
  private String description;

}