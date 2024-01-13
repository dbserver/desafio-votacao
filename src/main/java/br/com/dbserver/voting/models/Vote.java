package br.com.dbserver.voting.models;

import br.com.dbserver.voting.enums.TypeVote;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Enumerated(EnumType.STRING)
    private TypeVote typeVote;

    public Vote() {
    }

    public Vote(UUID id, Associate associate, Schedule schedule, TypeVote typeVote) {
        this.id = id;
        this.associate = associate;
        this.schedule = schedule;
        this.typeVote = typeVote;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Associate getAssociate() {
        return associate;
    }

    public void setAssociate(Associate associate) {
        this.associate = associate;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public TypeVote getTypeVote() {
        return typeVote;
    }

    public void setTypeVote(TypeVote typeVote) {
        this.typeVote = typeVote;
    }
}
