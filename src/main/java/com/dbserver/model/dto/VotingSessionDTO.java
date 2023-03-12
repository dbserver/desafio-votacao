package com.dbserver.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VotingSessionDTO {

    private String id;
    private String idAgenda;
    private Long duration;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
