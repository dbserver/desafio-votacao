package com.db.polling.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResultResponse {

  Long yesVotes;
  Long noVotes;
  Long totalVotes;

}
