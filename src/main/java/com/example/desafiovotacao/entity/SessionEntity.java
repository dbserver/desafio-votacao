package com.example.desafiovotacao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(schema = "voteschallenge", name = "session")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer duration;
    @Column(insertable = false, updatable = false)
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "ruling_id")
    private RulingEntity ruling;

    public boolean isSessionRunning() {
        if(this.creationDate != null) {
            return (Instant.now().getEpochSecond() - this.creationDate.toInstant().getEpochSecond()) <= this.duration;
        }

        return false;
    }
}