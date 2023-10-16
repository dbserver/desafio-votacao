package com.challenge.stubs;

import com.challenge.model.Stave;

import java.util.Optional;

public interface StaveStub {
    static Stave build(Long id) {
        return Stave.builder()
                .id(id)
                .title("Stave Test")
                .build();
    }
}
