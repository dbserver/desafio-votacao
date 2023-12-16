package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "AgendaResponse", description = "Body Response of an Agenda")
public class AgendaResponse {

    @Schema(name = "agenda_id", example = "1", description = "ID of created Agenda")
    @JsonProperty("agenda_id")
    private Long agendaId;

    @Schema(name = "agenda_name", example = "Approve new director", description = "Name of the Agenda to be created")
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

    @Schema(name = "agenda_status", example = "FINISHED", description = "Agenda status")
    @JsonProperty("agenda_status")
    private AgendaStatus agendaStatus;

    @Schema(name = "total_votes", example = "10", description = "Total votes")
    @JsonProperty("total_votes")
    private Long totalVotes;

    @Schema(name = "yes_votes", example = "4", description = "Total of Yes votes")
    @JsonProperty("yes_votes")
    private Long yesVotes;

    @Schema(name = "no_votes", example = "6", description = "Total of No votes")
    @JsonProperty("no_votes")
    private Long noVotes;

    @Schema(name = "agenda_result", example = "false", description = "Result of votation, Yes or no (true/false)")
    @JsonProperty("agenda_result")
    private Boolean agendaResult;

    @Schema(name = "agenda_result_description", example = "NO is the most votaded", description = "A little description of the result")
    @JsonProperty("agenda_result_description")
    private String agendaResultDescription;
}
