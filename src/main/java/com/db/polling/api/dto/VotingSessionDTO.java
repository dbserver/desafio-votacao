package com.db.polling.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingSessionDTO {

  private Long agendaId;
  private LocalDateTime openingTime;
  private LocalDateTime closingTime;
}
