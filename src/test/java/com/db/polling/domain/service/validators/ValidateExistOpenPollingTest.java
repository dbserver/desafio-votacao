package com.db.polling.domain.service.validators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.repository.VotingSessionRepository;
import com.db.polling.domain.exception.UnprocessableException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ValidateExistOpenPollingTest {

  @Mock
  private VotingSessionRepository repositoryMock;

  @InjectMocks
  private ValidateExistOpenPolling validator;

  @Test
  public void testValidateThrowsUnprocessableException() {
    AgendaEntity agendaEntity = new AgendaEntity();
    agendaEntity.setAgendaId(1L);

    ArgumentCaptor<LocalDateTime> captor = ArgumentCaptor.forClass(LocalDateTime.class);
    when(repositoryMock.existsByClosingTimeLessThanEqualAndAgendaEntityAgendaId(captor.capture(),
        anyLong())).thenReturn(true);

    UnprocessableException exception = assertThrows(UnprocessableException.class,
        () -> validator.validate(agendaEntity));
    assertEquals("VOTE-006", exception.getCode());

    LocalDateTime capturedValue = captor.getValue();

    verify(repositoryMock).existsByClosingTimeLessThanEqualAndAgendaEntityAgendaId(
        eq(capturedValue), eq(1L));
  }

  @Test
  public void testValidateDoesNotThrowUnprocessableException() {
    AgendaEntity agendaEntity = new AgendaEntity();
    agendaEntity.setAgendaId(1L);

    ArgumentCaptor<LocalDateTime> captor = ArgumentCaptor.forClass(LocalDateTime.class);
    when(repositoryMock.existsByClosingTimeLessThanEqualAndAgendaEntityAgendaId(captor.capture(),
        anyLong()))
        .thenReturn(false);
    validator.validate(agendaEntity);

    LocalDateTime capturedValue = captor.getValue();

    verify(repositoryMock).existsByClosingTimeLessThanEqualAndAgendaEntityAgendaId(
        eq(capturedValue), eq(1L));
  }


}
