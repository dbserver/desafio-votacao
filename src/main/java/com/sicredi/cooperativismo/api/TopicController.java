package com.sicredi.cooperativismo.api;

import com.sicredi.cooperativismo.dto.request.TopicRequest;
import com.sicredi.cooperativismo.service.TopicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createTopic(@RequestBody @Valid TopicRequest topicRequest) {
        this.topicService.createTopic(topicRequest);
    }
}
