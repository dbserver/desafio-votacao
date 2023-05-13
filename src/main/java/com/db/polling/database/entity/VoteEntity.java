package com.db.polling.database.entity;


import com.db.polling.domain.enumeration.VoteEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vote")
public class VoteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "vote_id", nullable = false)
  private Long voteId;

  @ManyToOne
  @JoinColumn(name = "voting_session_id", nullable = false)
  private VotingSessionEntity votingSessionEntity;

  @ManyToOne
  @JoinColumn(name = "associate_id", nullable = false)
  private AssociateEntity associateEntity;

  @Column(name = "vote", nullable = false)
  @Enumerated(EnumType.STRING)
  private VoteEnum vote;

  @CreationTimestamp
  @Column(name = "creation_date", nullable = false)
  private LocalDateTime creationDate;
}
