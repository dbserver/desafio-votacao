package com.challenge.dto;

import com.challenge.enums.VoteEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteRequestDto {
    @NotNull
    private VoteEnum vote;
    @NotNull
    private Long associateId;
    @NotNull
    private Long staveSessionId;
}
