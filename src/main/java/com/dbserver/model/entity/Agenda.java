package com.dbserver.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Data
@Document(collection = "agenda")
@EqualsAndHashCode
public class Agenda {

    @Id
    private String id;
    private String title;
    private String description;
    @CreatedDate
    private LocalDateTime createdDate;

}
