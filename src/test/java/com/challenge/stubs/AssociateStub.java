package com.challenge.stubs;

import com.challenge.model.Associate;

public interface AssociateStub {
    static Associate build(){
        return Associate.builder()
                .id(10L)
                .name("Associate Test")
                .document("99999999999")
                .build();
    }
}
