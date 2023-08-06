package com.sicredi.cooperativismo.service;

import com.sicredi.cooperativismo.domain.Topic;
import com.sicredi.cooperativismo.dto.request.TopicRequest;

public interface ITopicService {
    Topic createTopic(TopicRequest topicRequest);
}
