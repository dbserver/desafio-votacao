package com.dbserver.model.dto;

import com.dbserver.model.enums.VotingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VotingAgendaDTO {

    private String id;
    private VotingStatus votingStatus;
    private Long duration;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer votesAgaist;
    private Integer votesInFavor;

}
