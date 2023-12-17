package com.challenge.votation.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "agenda")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime startDate;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "agendaEntity")
    private Set<VoteEntity> votes;
}
