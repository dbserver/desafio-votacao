package com.db.polling.api.dto.response;


import com.db.polling.domain.enumeration.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponse {

  VoteEnum vote;

}
