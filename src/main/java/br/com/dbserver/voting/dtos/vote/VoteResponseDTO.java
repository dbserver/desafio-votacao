package br.com.dbserver.voting.dtos.vote;

import java.io.Serializable;

public class VoteResponseDTO implements Serializable {

    private String titleSchedule;
    private String nameAssociate;
    private String vote;

    public VoteResponseDTO() {
    }

    public VoteResponseDTO(String titleSchedule, String nameAssociate, String vote) {
        this.titleSchedule = titleSchedule;
        this.nameAssociate = nameAssociate;
        this.vote = vote;
    }

    public String getTitleSchedule() {
        return titleSchedule;
    }

    public void setTitleSchedule(String titleSchedule) {
        this.titleSchedule = titleSchedule;
    }

    public String getNameAssociate() {
        return nameAssociate;
    }

    public void setNameAssociate(String nameAssociate) {
        this.nameAssociate = nameAssociate;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

}
