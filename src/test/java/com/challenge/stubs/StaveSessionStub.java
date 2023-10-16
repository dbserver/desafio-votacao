package com.challenge.stubs;

import com.challenge.enums.SessionStatusEnum;
import com.challenge.model.Stave;
import com.challenge.model.StaveSession;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public interface StaveSessionStub {
    static StaveSession build(Long id) {
        return StaveSession.builder()
                .duration(1)
                .id(id != null ? id : RandomUtils.nextLong())
                .status(SessionStatusEnum.OPEN)
                .stave(Stave.builder().id(1L).title("Stave Test").build())
                .build();
    }

    static StaveSession build() {
        return build(null);
    }

    static StaveSession buildClose(Long id) {
        return StaveSession.builder()
                .duration(1)
                .id(id != null ? id : RandomUtils.nextLong())
                .status(SessionStatusEnum.CLOSE)
                .stave(Stave.builder().id(1L).title("Stave Test").build())
                .build();
    }

    static StaveSession buildClose() {
        return buildClose(null);
    }
}
