package com.challenge.utils;

import org.apache.commons.lang3.RandomUtils;

public interface StubsUtil {
    final static long START_INCLUSIVE = 1L;
    final static long END_EXCLUSIVE = 9999L;

    static Long nextId() {
        return RandomUtils.nextLong(START_INCLUSIVE, END_EXCLUSIVE);
    }
}
