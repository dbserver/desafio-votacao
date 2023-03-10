package com.dbserver.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteCreatedDTO {

    @NotBlank
    private String idAgenda;
    @NotNull
    private Boolean vote;
    @NotBlank
    private String cpf;

}
