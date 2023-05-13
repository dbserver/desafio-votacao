package com.db.polling.domain.service.validators;

import com.db.polling.database.entity.AgendaEntity;
import com.db.polling.database.entity.VotingSessionEntity;
import com.db.polling.domain.config.TimeSessionProperties;
import com.db.polling.domain.exception.UnprocessableException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateTimeSession implements VoteSessionValidation {

  @Autowired
  private TimeSessionProperties timeSessionProperties;

  @Override
  public void validate(AgendaEntity agendaEntity) {

    List<VotingSessionEntity> invalidSessions = agendaEntity.getVotingSessionEntity()
        .stream().filter(session -> !session
            .isValidSession(timeSessionProperties.getDurationMinutes())).toList();

    if (!invalidSessions.isEmpty()) {
      throw new UnprocessableException("Th duration of the voting session is not correct");
    }
  }

}
