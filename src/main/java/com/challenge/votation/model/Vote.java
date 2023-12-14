package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class Vote {
    @NotNull(message = "{clientVote.not.null}")
    @JsonProperty("client_vote")
    private Boolean clientVote;

    @NotBlank(message = "{clientId.not.blank}")
    @JsonProperty("client_id")
    private String clientId;
}
