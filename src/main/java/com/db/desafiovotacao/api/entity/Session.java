package com.db.desafiovotacao.api.entity;

import com.db.desafiovotacao.api.domain.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime dataBegin;

    private LocalDateTime dataEnd;

    @ManyToOne
    @JoinColumn(name = "agenda_id", referencedColumnName = "id")
    private Agenda agenda;

    @Enumerated(EnumType.STRING)
    private Status status;
}
