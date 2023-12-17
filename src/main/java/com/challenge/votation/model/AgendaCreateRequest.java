package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "AgendaCreateRequest", description = "Body Request to create an Agenda")
public class AgendaCreateRequest {

    @Schema(name = "agenda_name", example = "Approve new director", description = "Name of the Agenda to be created")
    @NotBlank(message = "{agendaName.not.blank}")
    @JsonProperty("agenda_name")
    private String agendaName;
}
