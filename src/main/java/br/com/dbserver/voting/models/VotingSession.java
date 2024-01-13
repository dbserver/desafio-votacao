package br.com.dbserver.voting.models;

import br.com.dbserver.voting.enums.StatusVotingSession;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "voting_session")
public class VotingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Column(name = "start_session")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime start;

    @Column(name = "end_session")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime end;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusVotingSession status;

    public VotingSession() {
    }

    public VotingSession(UUID id, Schedule schedule, LocalDateTime start, LocalDateTime end, StatusVotingSession status) {
        this.id = id;
        this.schedule = schedule;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public StatusVotingSession getStatus() {
        return status;
    }

    public void setStatus(StatusVotingSession status) {
        this.status = status;
    }
}
