package com.db.polling.database.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VotingSessionEntityTest {

  private VotingSessionEntity votingSession;

  @BeforeEach
  public void setUp() {
    votingSession = new VotingSessionEntity();
  }

  @Test
  public void isValidSessionWithOpeningDateAfterCurrentDateShouldReturnFalse() {
    votingSession.setOpeningTime(LocalDateTime.now().plusMinutes(5));
    assertFalse(votingSession.isValidSession(10));
  }

  @Test
  public void isValidSessionWithClosingDateBeforeOpeningDateShouldReturnFalse() {
    votingSession.setOpeningTime(LocalDateTime.now().minusMinutes(5));
    votingSession.setClosingTime(LocalDateTime.now().minusMinutes(10));
    assertFalse(votingSession.isValidSession(10));
  }

  @Test
  public void isValidSessionWithValidSessionShouldReturnTrue() {
    votingSession.setOpeningTime(LocalDateTime.now().minusMinutes(10));
    assertTrue(votingSession.isValidSession(10));
  }

  @Test
  public void isClosedReturnFalse() {
    votingSession.setOpeningTime(LocalDateTime.now().minusMinutes(10));
    assertFalse(votingSession.isClosed());
  }

  @Test
  public void isClosedReturnTrue() {
    votingSession.setOpeningTime(LocalDateTime.now().plusMinutes(10));
    assertFalse(votingSession.isClosed());
  }

}
