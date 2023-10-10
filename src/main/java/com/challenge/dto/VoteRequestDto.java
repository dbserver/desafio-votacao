package com.challenge.dto;

import com.challenge.enums.VoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteRequestDto {
    private VoteEnum vote;
    private Long associateId;
    private Long staveSessionId;
}
