package com.db.polling.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voting_session")
public class VotingSessionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "voting_session_id", nullable = false)
  private Long votingSessionId;

  @ManyToOne
  @JoinColumn(name = "agenda_id", nullable = false)
  private AgendaEntity agendaEntity;

  @CreationTimestamp
  @Column(name = "creation_date", nullable = false)
  private LocalDateTime creationDate;

  @Column(name = "opening_date", nullable = false)
  private LocalDateTime openingTime;

  @Column(name = "closing_date", nullable = false)
  private LocalDateTime closingTime;

  @OneToMany(mappedBy = "votingSessionEntity")
  private List<VoteEntity> voteEntities = new ArrayList<>();

  public boolean isValidSession(int durationMinutes) {
    LocalDateTime now = LocalDateTime.now();
    if (openingTime == null || openingTime.isAfter(now)) {
      return false;
    }
    if (closingTime != null && closingTime.isBefore(openingTime)) {
      return false;
    }
    if (closingTime == null || closingTime.isBefore(openingTime.plusMinutes(durationMinutes))) {
      closingTime = openingTime.plusMinutes(durationMinutes);
    }
    return true;
  }

  public boolean isClosed() {
    return closingTime != null && closingTime.isBefore(LocalDateTime.now());
  }

}
