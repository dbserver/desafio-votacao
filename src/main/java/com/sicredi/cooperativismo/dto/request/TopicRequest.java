package com.sicredi.cooperativismo.dto.request;

import java.time.LocalDateTime;

public class TopicRequest {

    public String title;

    public LocalDateTime startTime = LocalDateTime.now();

    public LocalDateTime endTime = LocalDateTime.now().plusMinutes(1);
}
