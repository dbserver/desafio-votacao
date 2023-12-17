package com.challenge.votation.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "votes")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String clientId;

    private boolean vote;

    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private AgendaEntity agendaEntity;
}
