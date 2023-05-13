package com.db.polling.api.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaWrapperResponse extends AbstractHeaderDTO {

  private List<AgendaResponse> agendaResponses;

}
