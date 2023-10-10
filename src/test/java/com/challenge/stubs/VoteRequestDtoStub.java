package com.challenge.stubs;

import com.challenge.dto.VoteRequestDto;
import com.challenge.enums.VoteEnum;

public interface VoteRequestDtoStub {
    static VoteRequestDto build(){
        return VoteRequestDto.builder()
                .vote(VoteEnum.YES)
                .associateId(1L)
                .staveSessionId(1L)
                .build();
    }
}
