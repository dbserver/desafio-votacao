package com.db.polling.database.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "associate")
public class AssociateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "associate_id", nullable = false)
  private Long associateId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "cpf", nullable = false, unique = true)
  private String cpf;

  @CreationTimestamp
  @Column(name = "creation_date", nullable = false)
  private LocalDateTime creationDate;

}
