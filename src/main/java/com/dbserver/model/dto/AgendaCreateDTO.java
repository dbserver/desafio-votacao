package com.dbserver.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgendaCreateDTO {

    @NotBlank
    private String title;
    @NotBlank
    private String description;

}
