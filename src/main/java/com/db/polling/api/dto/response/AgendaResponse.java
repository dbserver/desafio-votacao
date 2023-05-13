package com.db.polling.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaResponse {

  private Long agendaId;

  private String title;

  private String description;

}
