package com.challenge.stubs;

import com.challenge.enums.VoteEnum;
import com.challenge.model.StaveSession;
import com.challenge.model.Vote;

public interface VoteStub {
    static Vote build(StaveSession session) {
        return Vote.builder()
                .vote(VoteEnum.YES)
                .associate(AssociateStub.build(1L))
                .staveSession(session != null ? session : StaveSessionStub.build())
                .build();

    }
}
