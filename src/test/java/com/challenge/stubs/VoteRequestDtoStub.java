package com.challenge.stubs;

import com.challenge.dto.VoteRequestDto;
import com.challenge.enums.VoteEnum;
import org.apache.commons.lang3.RandomUtils;

import java.util.Collections;
import java.util.Map;

public interface VoteRequestDtoStub {
    static VoteRequestDto build(Map<String, Object> metadata) {
        return VoteRequestDto.builder()
                .vote(VoteEnum.YES)
                .associateId(Long.parseLong(metadata.getOrDefault("associateId", 1L).toString()))
                .staveSessionId(Long.parseLong(metadata.getOrDefault("staveSessionId", 1L).toString()))
                .build();
    }

    static VoteRequestDto build() {
        return build(Collections.emptyMap());
    }
}
