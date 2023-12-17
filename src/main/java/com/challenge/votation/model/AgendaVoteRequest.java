package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "AgendaVoteRequest", description = "Body Request to vote")
public class AgendaVoteRequest {

    @Schema(name = "client_vote", example = "true", description = "Vote Yes or No (true or false)")
    @NotNull(message = "{clientVote.not.null}")
    @JsonProperty("client_vote")
    private Boolean clientVote;

    @Schema(name = "client_id", example = "gustavo.gonzaga", description = "Unique client Id")
    @NotBlank(message = "{clientId.not.blank}")
    @JsonProperty("client_id")
    private String clientId;
}
