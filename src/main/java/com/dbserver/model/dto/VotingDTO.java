package com.dbserver.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VotingDTO {

    private String id;
    private String idAgenda;
    private Long duration;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
