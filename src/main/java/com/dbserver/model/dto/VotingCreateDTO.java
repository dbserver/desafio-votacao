package com.dbserver.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VotingCreateDTO {
    @NotNull
    private String  idAgenda;
    @NotNull
    private Long duration;
}
