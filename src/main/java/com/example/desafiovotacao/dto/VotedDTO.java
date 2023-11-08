package com.example.desafiovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class VotedDTO {
    private Integer voteId;
    private Integer sessionId;
    private String cpfAssociate;
    private String computedVote;
    private String sessionDate;
    private String topic;
}