package com.challenge.stubs;

import com.challenge.enums.SessionStatusEnum;
import com.challenge.model.Stave;
import com.challenge.model.StaveSession;
import com.challenge.utils.StubsUtil;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

import static com.challenge.utils.StubsUtil.nextId;

public interface StaveSessionStub {
    static StaveSession build(Long id, Stave stave) {
        return StaveSession.builder()
                .duration(1)
                .id(id != null ? id : nextId())
                .status(SessionStatusEnum.OPEN)
                .stave(stave != null ? stave : Stave.builder().id(nextId()).title("Stave Test").build())
                .build();
    }

    static StaveSession build() {
        return build(null, null);
    }

    static StaveSession buildClose(Long id) {
        return StaveSession.builder()
                .duration(1)
                .id(id != null ? id : nextId())
                .status(SessionStatusEnum.CLOSE)
                .stave(Stave.builder().id(1L).title("Stave Test").build())
                .build();
    }

    static StaveSession buildClose() {
        return buildClose(null);
    }
}
