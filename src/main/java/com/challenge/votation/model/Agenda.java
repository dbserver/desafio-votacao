package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Agenda {
    @JsonProperty("agenda_id")
    private Long agendaId;

    @NotBlank(message = "{agendaName.not.blank}")
    @JsonProperty("agenda_name")
    private String agendaName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("agenda_start")
    private LocalDateTime agendaStart;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("agenda_end")
    private LocalDateTime agendaEnd;

    @JsonProperty("agenda_status")
    private AgendaStatus agendaStatus;

    @JsonProperty("total_votes")
    private Long totalVotes;

    @JsonProperty("yes_votes")
    private Long yesVotes;

    @JsonProperty("no_votes")
    private Long noVotes;

    @JsonProperty("agenda_result")
    private Boolean agendaResult;

    @JsonProperty("agenda_result_description")
    private String agendaResultDescription;
}
