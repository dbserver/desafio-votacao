package br.com.dbserver.voting.models;

import br.com.dbserver.voting.enums.StatusVotingSessionEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "voting_session")
public class VotingSession implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

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
    private StatusVotingSessionEnum status;

    public VotingSession() {
    }

    public VotingSession(Integer id, Schedule schedule, LocalDateTime start, LocalDateTime end, StatusVotingSessionEnum status) {
        this.id = id;
        this.schedule = schedule;
        this.start = start;
        this.end = end;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public StatusVotingSessionEnum getStatus() {
        return status;
    }

    public void setStatus(StatusVotingSessionEnum status) {
        this.status = status;
    }
}
