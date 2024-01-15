package br.com.dbserver.voting.models;

import br.com.dbserver.voting.enums.TypeVoteEnum;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "vote")
public class Vote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "associate_id")
    private Associate associate;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(optional = false)
    @JoinColumn(name = "voting_session_id", nullable = false)
    private VotingSession votingSession;


    @Enumerated(EnumType.STRING)
    private TypeVoteEnum typeVote;

    public Vote() {
    }

    public Vote(Integer id, Associate associate, Schedule schedule, VotingSession votingSession, TypeVoteEnum typeVote) {
        this.id = id;
        this.associate = associate;
        this.schedule = schedule;
        this.votingSession = votingSession;
        this.typeVote = typeVote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public TypeVoteEnum getTypeVote() {
        return typeVote;
    }

    public void setTypeVote(TypeVoteEnum typeVoteEnum) {
        this.typeVote = typeVoteEnum;
    }

    public VotingSession getVotingSession() {
        return votingSession;
    }

    public void setVotingSession(VotingSession votingSession) {
        this.votingSession = votingSession;
    }
}
