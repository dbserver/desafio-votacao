package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "AgendaCreateResponse", description = "Body Response of create an Agenda")
public class AgendaCreateResponse {

    @Schema(name = "agenda_id", example = "1", description = "ID of created Agenda")
    @JsonProperty("agenda_id")
    private Long agendaId;

    @Schema(name = "agenda_name", example = "Approve new director", description = "Name of created Agenda")
    @JsonProperty("agenda_name")
    private String agendaName;
}
