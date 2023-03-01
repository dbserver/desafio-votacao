package br.tec.db.desafio.business.domain.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum StatusCpf {
    ABLE_TO_VOTE,
    UNABLE_TO_VOTE;

    private static final List<StatusCpf> VALUES =
            List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static StatusCpf randomStatusCpf()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
