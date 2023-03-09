package com.dbserver.model.enums;

import java.util.Optional;

public enum VotingStatus {

    OPEN("open"),
    APPROVED("approved"),
    DISAPPROVED("disapproved"),
    TIED("tied");

    private String id;

    VotingStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Optional<VotingStatus> getById(String id) {
        Optional<VotingStatus> status = Optional.empty();
        for (VotingStatus sts : VotingStatus.values()) {
            if (sts.getId().toLowerCase().equals(id.toLowerCase())) {
                status = Optional.of(sts);
            }
        }
        return status;
    }

}
