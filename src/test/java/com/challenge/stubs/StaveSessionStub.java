package com.challenge.stubs;

import com.challenge.enums.SessionStatusEnum;
import com.challenge.model.Stave;
import com.challenge.model.StaveSession;

public interface StaveSessionStub {
    static StaveSession build(){
        return StaveSession.builder()
                .duration(1)
                .id(10L)
                .status(SessionStatusEnum.OPEN)
                .stave(Stave.builder().id(1L).title("Stave Test").build())
                .build();
    }

    static StaveSession buildClose() {
        return StaveSession.builder()
                .duration(1)
                .id(10L)
                .status(SessionStatusEnum.CLOSE)
                .stave(Stave.builder().id(1L).title("Stave Test").build())
                .build();
    }
}
