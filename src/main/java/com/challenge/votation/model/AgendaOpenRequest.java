package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "AgendaOpenRequest", description = "Body Request to open an Agenda")
public class AgendaOpenRequest {

    @Schema(name = "agenda_end", example = "2023-12-16 18:00:00", description = "Date of Agenda close", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("agenda_end")
    private LocalDateTime agendaEnd;
}
