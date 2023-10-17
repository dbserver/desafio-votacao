package com.challenge.stubs;

import com.challenge.model.Associate;
import org.apache.commons.lang3.RandomUtils;

public interface AssociateStub {
    static Associate build(Long id) {
        return Associate.builder()
                .id(id != null ? id : RandomUtils.nextLong())
                .name("Associate Test")
                .document("99999999999")
                .build();
    }

    static Associate build() {
        return build(null);
    }
}
