package com.challenge.votation.model;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AgendaResponsePage extends PageImpl<AgendaResponse> {
    public AgendaResponsePage(List<AgendaResponse> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
