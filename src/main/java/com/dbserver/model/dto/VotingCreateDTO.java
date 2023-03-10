package com.dbserver.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotingCreateDTO {

    @NotNull
    private String  idAgenda;
    @NotNull
    private Long duration;

}
