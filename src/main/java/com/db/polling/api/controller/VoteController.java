package com.db.polling.api.controller;

import com.db.polling.api.dto.VoteDTO;
import com.db.polling.api.dto.response.VoteResponse;
import com.db.polling.domain.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/vote")
public class VoteController {

  private final VoteService voteService;

  public VoteController(VoteService voteService) {
    this.voteService = voteService;
  }

  @PostMapping("/{associateId}")
  public ResponseEntity<VoteResponse> vote(@PathVariable Long associateId, @Valid @RequestBody VoteDTO voteDTO) {
    VoteResponse result = voteService.vote(associateId, voteDTO);
    return ResponseEntity.ok(result);
  }
}
