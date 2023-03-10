package com.dbserver.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "vote")
@CompoundIndex(name = "unique_index", def = "{'idAgenda': 1, 'cpf': 1}", unique = true)
public class Vote {

    private String id;
    private String idAgenda;
    private String cpf;
    private Boolean vote;
    @CreatedDate
    private LocalDateTime createdDate;

}
