package br.com.dbserver.voting.dtos.votingsession;

import br.com.dbserver.voting.dtos.ScheduleDTO;

import java.io.Serializable;
import java.util.UUID;

public class VotingSessionResponseDTO implements Serializable {

    private UUID idSessionVoting;
    private ScheduleDTO schedule;
    private String votingStartTime;
    private String votingEndTime;
    private String status;

    public VotingSessionResponseDTO() {
    }

    public VotingSessionResponseDTO(UUID idSessionVoting, ScheduleDTO schedule, String votingStartTime, String votingEndTime, String status) {
        this.idSessionVoting = idSessionVoting;
        this.schedule = schedule;
        this.votingStartTime = votingStartTime;
        this.votingEndTime = votingEndTime;
        this.status = status;
    }

    public UUID getIdSessionVoting() {
        return idSessionVoting;
    }

    public void setIdSessionVoting(UUID idSessionVoting) {
        this.idSessionVoting = idSessionVoting;
    }

    public ScheduleDTO getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleDTO schedule) {
        this.schedule = schedule;
    }

    public String getVotingStartTime() {
        return votingStartTime;
    }

    public void setVotingStartTime(String votingStartTime) {
        this.votingStartTime = votingStartTime;
    }

    public String getVotingEndTime() {
        return votingEndTime;
    }

    public void setVotingEndTime(String votingEndTime) {
        this.votingEndTime = votingEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
