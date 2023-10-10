package com.challenge.controller;

import com.challenge.dto.VoteRequestDto;
import com.challenge.model.Vote;
import com.challenge.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/vote")
@AllArgsConstructor
public class VoteController {

    private final VoteService service;

    @PostMapping
    public ResponseEntity<Vote> save(@RequestBody @Validated VoteRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
    }
}
