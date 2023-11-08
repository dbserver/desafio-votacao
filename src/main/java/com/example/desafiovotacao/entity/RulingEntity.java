package com.example.desafiovotacao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(schema = "voteschallenge", name = "ruling")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RulingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String title;

    private String description;

    private Boolean results;

    private Date voteCountDate;

    @Column(insertable = false, updatable = false)
    private Date creationDate;

}
