package com.dbserver.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VoteDTO {

    private String id;
    private String idVoting;
    private String cpf;
    private Boolean vote;
    private LocalDateTime createdDate;

}
