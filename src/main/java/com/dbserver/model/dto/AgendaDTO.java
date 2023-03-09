package com.dbserver.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AgendaDTO {

    private String id;
    private String title;
    private String description;
    private LocalDateTime createdDate;

}
