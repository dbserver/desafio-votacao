package com.challenge.votation.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "DocumentResponse", description = "Body Response to validate a CPF document")
public class DocumentResponse {

    @Schema(name = "status", example = "ABLE_TO_VOTE", description = "Validate is if able to vote")
    @JsonProperty("status")
    private DocumentStatus status;
}
