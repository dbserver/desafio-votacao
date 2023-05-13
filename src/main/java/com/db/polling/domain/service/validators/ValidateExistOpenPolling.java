package com.db.polling.domain.service.validators;

import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.repository.VotingSessionRepository;
import com.db.polling.domain.exception.UnprocessableException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateExistOpenPolling implements VoteSessionValidation{

  @Autowired
  private VotingSessionRepository repository;

  @Override
  public void validate(AgendaEntity agendaEntity) {
    if (repository.existsByClosingTimeLessThanEqualAndAgendaEntityAgendaId(LocalDateTime.now(),
        agendaEntity.getAgendaId())) {
      throw new UnprocessableException("VOTE-006");
    }
  }
}
