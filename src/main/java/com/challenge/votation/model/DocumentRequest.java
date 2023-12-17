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
@Schema(name = "DocumentRequest", description = "Body Request to validate a CPF document")
public class DocumentRequest {

    @Schema(name = "cpf_number", example = "11989872000", description = "CPF document to be validate")
    @NotBlank(message = "{cpfNumber.not.blank}")
    @JsonProperty("cpf_number")
    private String cpfNumber;
}
