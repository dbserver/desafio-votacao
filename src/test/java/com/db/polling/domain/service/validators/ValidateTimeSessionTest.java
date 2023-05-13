package com.db.polling.domain.service.validators;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.entity.VotingSessionEntity;
import com.db.polling.domain.config.TimeSessionProperties;
import com.db.polling.domain.exception.UnprocessableException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidateTimeSessionTest {

  @Mock
  private TimeSessionProperties timeSessionProperties;

  @InjectMocks
  private ValidateTimeSession validator;

  @Test
  void shouldNotThrowExceptionWhenAllSessionsAreValid() {
    AgendaEntity agenda = buildAgenda();
    when(timeSessionProperties.getDurationMinutes()).thenReturn(30);
    assertDoesNotThrow(() -> validator.validate(agenda));
  }

  @Test
  void shouldThrowExceptionWhenSessionHasInvalidEndDate() {
    AgendaEntity agenda = buildAgenda();
    VotingSessionEntity invalidSession = new VotingSessionEntity();
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.minusMinutes(30);
    invalidSession.setOpeningTime(start);
    invalidSession.setClosingTime(end);
    agenda.setVotingSessionEntity(Arrays.asList(new VotingSessionEntity(), invalidSession));
    when(timeSessionProperties.getDurationMinutes()).thenReturn(30);
    assertThrows(UnprocessableException.class, () -> validator.validate(agenda));
  }

  private AgendaEntity buildAgenda() {
    AgendaEntity agenda = new AgendaEntity();
    VotingSessionEntity validSession = new VotingSessionEntity();
    LocalDateTime start = LocalDateTime.now();
    LocalDateTime end = start.plusMinutes(30);
    validSession.setOpeningTime(start);
    validSession.setClosingTime(end);
    agenda.setVotingSessionEntity(Collections.singletonList(validSession));

    return agenda;
  }

}

