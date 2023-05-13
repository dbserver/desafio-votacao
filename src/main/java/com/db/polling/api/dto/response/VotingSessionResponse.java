package com.db.polling.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingSessionResponse {

  private Long id;
  private Long agendaId;
  private LocalDateTime openingTime;
  private LocalDateTime closingTime;
}
