package br.com.dbserver.voting.dtos.votingsession;

import br.com.dbserver.voting.dtos.ScheduleDTO;

import java.io.Serializable;

public class VotingSessionResponseDTO implements Serializable {

    private Integer idSessionVoting;
    private ScheduleDTO schedule;
    private String status;

    public VotingSessionResponseDTO() {
    }

    public VotingSessionResponseDTO(Integer idSessionVoting, ScheduleDTO schedule, String votingStartTime, String votingEndTime, String status) {
        this.idSessionVoting = idSessionVoting;
        this.schedule = schedule;
        this.status = status;
    }

    public Integer getIdSessionVoting() {
        return idSessionVoting;
    }


    public ScheduleDTO getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleDTO schedule) {
        this.schedule = schedule;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
