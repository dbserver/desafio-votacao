package com.db.polling.api.controller;

import com.db.polling.api.dto.VotingResultDTO;
import com.db.polling.api.dto.VotingSessionDTO;
import com.db.polling.api.dto.response.VoteResultResponse;
import com.db.polling.domain.service.VotingSessionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/voting-sessions")
public class VotingSessionController {

  private final VotingSessionService votingSessionService;

  public VotingSessionController(VotingSessionService votingSessionService) {
    this.votingSessionService = votingSessionService;
  }

  @PostMapping
  public ResponseEntity<VotingSessionDTO> createVotingSession(
      @Valid @RequestBody VotingSessionDTO createVotingSessionDTO) {
    VotingSessionDTO votingSessionDTO = votingSessionService.createVotingSession(
        createVotingSessionDTO);

    return ResponseEntity.ok().body(votingSessionDTO);
  }


  @GetMapping("/{id}")
  public ResponseEntity<VotingSessionDTO> getVotingSession(@PathVariable Long id) {
    VotingSessionDTO votingSessionDTO = votingSessionService.getVotingSession(id);
    return ResponseEntity.ok().body(votingSessionDTO);
  }

  @GetMapping("/{id}/result")
  public ResponseEntity<VoteResultResponse> getResult(@PathVariable Long id) {
    VoteResultResponse resultDTO = votingSessionService.getResult(id);
    return ResponseEntity.ok(resultDTO);
  }
}
