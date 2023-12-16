package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "AgendaOpenResponse", description = "Body Response of open an Agenda")
public class AgendaOpenResponse {

    @Schema(name = "agenda_id", example = "1", description = "ID of created Agenda")
    @JsonProperty("agenda_id")
    private Long agendaId;

    @Schema(name = "agenda_name", example = "Approve new director", description = "Name of created Agenda")
    @JsonProperty("agenda_name")
    private String agendaName;

    @Schema(name = "agenda_start", example = "2023-12-16 17:00:00", description = "Date of Agenda open")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("agenda_start")
    private LocalDateTime agendaStart;

    @Schema(name = "agenda_end", example = "2023-12-16 18:00:00", description = "Date of Agenda close")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("agenda_end")
    private LocalDateTime agendaEnd;
}
