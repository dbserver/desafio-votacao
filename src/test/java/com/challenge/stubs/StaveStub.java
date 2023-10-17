package com.challenge.stubs;

import com.challenge.model.Stave;
import com.challenge.utils.StubsUtil;

import java.util.Optional;

import static com.challenge.utils.StubsUtil.nextId;

public interface StaveStub {
    static Stave build(Long id) {
        return Stave.builder()
                .id(id)
                .title("Stave Test")
                .build();
    }

    static Stave build() {
        return build(nextId());
    }
}
