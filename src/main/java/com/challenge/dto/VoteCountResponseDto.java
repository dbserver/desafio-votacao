package com.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteCountResponseDto {
    private String staveTitle;
    private Integer total;
    private Long yesVotes;
    private Long noVotes;
}
