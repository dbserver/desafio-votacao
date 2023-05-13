package com.db.polling.database.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agenda")
public class AgendaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "agenda_id", nullable = false)
  private Long agendaId;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @OneToMany(mappedBy = "agendaEntity")
  private List<VotingSessionEntity> votingSessionEntity = new ArrayList<>();

  @CreationTimestamp
  @Column(name = "creation_date", nullable = false)
  private LocalDateTime creationDate;
}
