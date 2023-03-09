package com.dbserver.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgendaVotingDTO {

    private String idAgenda;
    private String title;
    private String description;
    private VotingAgendaDTO voting;

}
