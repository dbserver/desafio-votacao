package com.db.polling.api.dto;


import com.db.polling.domain.enumeration.VoteEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {


  @NotNull(message = "Voting session ID is required")
  private Long votingSessionId;

  @NotNull(message = "Associate ID is required")
  private Long associateId;

  @NotNull(message = "Vote value is required")
  private VoteEnum vote;

}
